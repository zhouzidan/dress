package com.dudress.dress.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dudress.dress.R;
import com.dudress.dress.bean.ResultBean;
import com.dudress.dress.util.GsonUtils;
import com.dudress.dress.util.NetUtils;
import com.dudress.dress.util.SharedpreUtil;
import com.dudress.dress.util.ToastUtil;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by zhou on 16-3-17.
 */
public class SignUpActivity extends BaseActivity {


    @Bind(R.id.ed_email)
    EditText emailEditText ;
    @Bind(R.id.ed_pwd)
    EditText passwordEditText;

    @Bind(R.id.btn_signup)
    Button signUpBtn;
    @Bind(R.id.tv_signin)
    TextView signInTextView;

    @Bind(R.id.tv_msg)
    TextView tvMsg;

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
        setContentView(R.layout.activity_account_signup_1);
        ButterKnife.bind(this);
        tvMsg.setText(Html.fromHtml(getString(R.string.msg_info_protocal)));
        initView();
    }

@OnClick(R.id.btn_signup)
void clickSignUp(){
    String email = emailEditText.getText().toString().trim();
    String password = passwordEditText.getText().toString().trim();
    getValidateVode(email,password);
}
    @OnClick(R.id.tv_signin)
    void clickSignIn(){
        startActivity(new Intent(mContext,LoginActivity.class));
        finish();
    }


    @Override
    protected void onStop() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        SharedpreUtil.setStringToSP(mContext,KEY_EMAIL_CAHCE,email);
        SharedpreUtil.setStringToSP(mContext,KEY_PASSWORD_CAHCE,password);
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && RESULT_OK == resultCode){
            String code = data.getStringExtra(SignUpEmailCodeActivity.KEY_EMAIL_CODE);
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            signUp(email,password,code);
            showLoadingDialog();
        }
    }


    private void initView(){
        String email = SharedpreUtil.getStringFromSP(mContext,KEY_EMAIL_CAHCE);
        String password = SharedpreUtil.getStringFromSP(mContext,KEY_PASSWORD_CAHCE);
        if (TextUtils.isEmpty(email) == false)
            emailEditText.setText(email);
        if (TextUtils.isEmpty(password) == false)
            passwordEditText.setText(password);
    }
    private void getValidateVode(String email, String password){
        if (TextUtils.isEmpty(email) == true){
            ToastUtil.show(R.string.msg_error_email_empty);
        }else if(email.matches(Patterns.EMAIL_ADDRESS.pattern()) == false){
            ToastUtil.show(R.string.msg_error_email_invalid);
        }else if(TextUtils.isEmpty(password) == true){
            ToastUtil.show(R.string.msg_error_pwd_empty);
        }else if(password.length() < 6 || password.length() > 16){
            ToastUtil.show(R.string.msg_error_pwd_invalid);
        } else {
            RequestParams requestParams = new RequestParams();
            requestParams.add("email",email);
            NetUtils.post("Account/getvalidatecode", requestParams, getValidateCodeHandler);
            showLoadingDialog();
        }
    }

    private void signUp(String email, String password,String code){
        RequestParams requestParams = new RequestParams();
        requestParams.add("method","signup");
        requestParams.add("datatype","email");
        requestParams.add("data",email);
        requestParams.add("validateCode",code);
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
