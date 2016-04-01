package com.dudress.dress.account;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.dudress.dress.R;
import com.dudress.dress.bean.ResultBean;
import com.dudress.dress.util.GsonUtils;
import com.dudress.dress.util.NetUtils;
import com.dudress.dress.util.SharedpreUtil;


import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by zhou on 16-3-17.
 */
public class SignUpActivity extends Activity implements View.OnClickListener{
    EditText emailEditText,passwordEditText, noteNameEditText;
    ImageView pwdVisibleImg;
    int KEY_TAG_PWD_VISIBLE = 1;
    private String TAG = getClass().getSimpleName();
    private Context mContext = this;
    private int REQUEST_CODE = 101 ;

    private String KEY_EMAIL_CAHCE = "key_email_cache";
    private String KEY_PASSWORD_CAHCE = "key_password_cache";
    private String KEY_NOTENAME_CAHCE = "key_notename_cache";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_registe);
        findViewById(R.id.img_back).setOnClickListener(this);
        findViewById(R.id.tv_back).setOnClickListener(this);
        emailEditText = (EditText) findViewById(R.id.ed_username);
        passwordEditText = (EditText) findViewById(R.id.ed_pwd);
        noteNameEditText = (EditText) findViewById(R.id.ed_notename);
        pwdVisibleImg = (ImageView)findViewById(R.id.img_pwd_visible);
        pwdVisibleImg.setOnClickListener(this);
        pwdVisibleImg.setTag(false);
        findViewById(R.id.btn_registe).setOnClickListener(this);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
            case R.id.tv_back:
                finish();
                break;
            case R.id.img_pwd_visible:
                boolean last_visible = (Boolean)pwdVisibleImg.getTag();
                pwdVisibleImg.setTag(!last_visible);
                if (last_visible){
                    pwdVisibleImg.setImageResource(R.mipmap.hide_blue);
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    pwdVisibleImg.setImageResource(R.mipmap.display_blue);
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            case R.id.btn_registe:
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String noteName = noteNameEditText.getText().toString().trim();
                getValidateVode(email,password,noteName);
                break;
        }
    }

    @Override
    protected void onStop() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String noteName = noteNameEditText.getText().toString().trim();
        SharedpreUtil.setStringToSP(mContext,KEY_EMAIL_CAHCE,email);
        SharedpreUtil.setStringToSP(mContext,KEY_PASSWORD_CAHCE,password);
        SharedpreUtil.setStringToSP(mContext,KEY_NOTENAME_CAHCE,noteName);
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && RESULT_OK == resultCode){
            String code = data.getStringExtra(SignUpEmailCodeActivity.KEY_EMAIL_CODE);
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String noteName = noteNameEditText.getText().toString().trim();
            signUp(email,password,noteName,code);
            showLoadingDialog();
        }
    }


    private void initView(){
        String email = SharedpreUtil.getStringFromSP(mContext,KEY_EMAIL_CAHCE);
        String password = SharedpreUtil.getStringFromSP(mContext,KEY_PASSWORD_CAHCE);
        String notename = SharedpreUtil.getStringFromSP(mContext,KEY_NOTENAME_CAHCE);
        emailEditText.setText(email != null ? email:null);
        passwordEditText.setText(password != null ? password:null);
        noteNameEditText.setText(notename != null ? notename:null);
    }
    private void getValidateVode(String email, String password, String noteName){
        if (TextUtils.isEmpty(email) == true){
            Toast.makeText(mContext,R.string.msg_error_email_empty,Toast.LENGTH_SHORT).show();
        }else if(email.matches(Patterns.EMAIL_ADDRESS.pattern()) == false){
            Toast.makeText(this,R.string.msg_error_email_invalid,Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password) == true){
            Toast.makeText(this,R.string.msg_error_pwd_empty,Toast.LENGTH_SHORT).show();
        }else if(password.length() < 6 || password.length() > 16){
            Toast.makeText(mContext,R.string.msg_error_pwd_invalid,Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(noteName)){
            Toast.makeText(mContext,R.string.msg_error_notename_empty,Toast.LENGTH_SHORT).show();
        }else {
            RequestParams requestParams = new RequestParams();
            requestParams.add("type","signup");
            requestParams.add("datatype","email");
            requestParams.add("data",email);
            NetUtils.post("getvalidatecode.php", requestParams, getValidateCodeHandler);
            showLoadingDialog();
        }
    }

    private void signUp(String email, String password, String noteName,String code){
        RequestParams requestParams = new RequestParams();
        requestParams.add("method","signup");
        requestParams.add("datatype","email");
        requestParams.add("data",email);
        requestParams.add("validateCode",code);
        requestParams.add("notename",noteName);
        requestParams.add("password",password);
        NetUtils.post("account.php", requestParams, signUpResponseHandler);
        showLoadingDialog();
    }

    private JsonHttpResponseHandler getValidateCodeHandler = new JsonHttpResponseHandler(){

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Log.d(TAG,"onSuccess:"+response);
            ResultBean resultBean = GsonUtils.getInstance().fromJson(response.toString(),ResultBean.class);
            if (resultBean.getCode() == 0){
                Intent intent = new Intent(mContext,SignUpEmailCodeActivity.class);
                String email = emailEditText.getText().toString().trim();
                intent.putExtra(SignUpEmailCodeActivity.KEY_EMAIL,email);
                startActivityForResult(intent,REQUEST_CODE);
            }else if(resultBean.getCode() == 101002){
                Toast.makeText(mContext,R.string.msg_error_email_has_signup,Toast.LENGTH_SHORT).show();
            }else if(resultBean.getCode() == 101001){
                Toast.makeText(mContext,R.string.msg_error_params_exception,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
            Log.d(TAG,"onFailure:"+responseString);
        }

        @Override
        public void onFinish() {
            super.onFinish();
            cancelLoadingDialog();
        }
    };

    private JsonHttpResponseHandler signUpResponseHandler = new JsonHttpResponseHandler(){

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Toast.makeText(mContext,R.string.signup_success,Toast.LENGTH_SHORT).show();
            Log.d(TAG,"onSuccess:"+response);
            ResultBean resultBean = GsonUtils.getInstance().fromJson(response.toString(),ResultBean.class);
            if (resultBean.getCode() == 0){
            }
        }


        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
        }

        @Override
        public void onFinish() {
            super.onFinish();
            cancelLoadingDialog();
        }
    };

    ProgressDialog dialog;
    private void showLoadingDialog(){
        dialog = ProgressDialog.show(this, null,getString(R.string.msg_loading), true);
    }
    private void cancelLoadingDialog(){
        if (dialog != null) {
            dialog.cancel();
        }
    }
}
