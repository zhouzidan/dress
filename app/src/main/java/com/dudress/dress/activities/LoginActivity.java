package com.dudress.dress.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dudress.dress.R;
import com.dudress.dress.db.AccountDBManager;
import com.dudress.dress.db.models.Account;
import com.dudress.dress.util.NetUtils;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import cz.msebera.android.httpclient.Header;

/**
 * Created by zhou on 16/3/12.
 */
public class LoginActivity extends BaseActivity  {

    Context mContext = this;

    @Bind(R.id.ed_email)
    EditText emailEditText ;
    @Bind(R.id.ed_pwd)
    EditText passwordEditText;

    @Bind(R.id.tv_foeget_pwd)
    TextView forgetPwdTextView;
    @Bind(R.id.btn_login)
    Button loginBtn;
    @Bind(R.id.tv_register)
    TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login_1);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.tv_foeget_pwd)
    void clickForgetPwd(){
        startActivity(new Intent(this, ForgetPwdActivity.class));
        finish();
    }

    @OnClick(R.id.btn_login)
    void clickLogin(){
        Toast.makeText(this,"login / sign in",Toast.LENGTH_SHORT).show();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email) == true){
            Toast.makeText(mContext,R.string.msg_error_email_empty,Toast.LENGTH_SHORT).show();
        }else if(email.matches(Patterns.EMAIL_ADDRESS.pattern()) == false){
            Toast.makeText(this,R.string.msg_error_email_invalid,Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password) == true){
            Toast.makeText(this,R.string.msg_error_pwd_empty,Toast.LENGTH_SHORT).show();
        }else {
            RequestParams requestParams = new RequestParams();
            requestParams.add("type","login");
            requestParams.add("email",email);
            requestParams.add("password",password);
            NetUtils.post("login.php", requestParams, loginHandler);
            showLoadingDialog();
        }

    }

    @OnClick(R.id.tv_register)
    void clickSignUp(){
        startActivity(new Intent(this, SignUpActivity.class));// 跳转到注册页面
        finish();
    }

    //软件盘的done/完成按钮的事件
    @OnEditorAction(R.id.ed_pwd)
    boolean editDownPwd(TextView v, int actionId, KeyEvent event){
        if (actionId == EditorInfo.IME_ACTION_DONE)
            clickLogin();
        return  false;
    }

    //登陆操作的结果回调方法
    private JsonHttpResponseHandler loginHandler = new JsonHttpResponseHandler(){
        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
//TODO
            String email = "";
            String password = "";
            String userId = "";
            String picUrl = "";
            String noteName = "";
            long loginTime = System.currentTimeMillis();

            AccountDBManager.get().deleteByEmail(email);

            Account account = new Account();
            account.setPassword(password);
            account.setEmail(email);
            account.setLoginTime(loginTime);
            account.setUserid(userId);
            account.setNotename(noteName);
            account.setStatus(AccountDBManager.ACCOUNT_STATUS_ON);
            account.save();


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
    } ;
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
