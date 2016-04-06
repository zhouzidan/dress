package com.dudress.dress.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dudress.dress.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhou on 16/3/12.
 */
public class Login1Activity extends Activity  {
    @Bind(R.id.tv_title)
    TextView titleTextView;

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

        titleTextView.setText(R.string.login_btn_text);
    }


    @OnClick(R.id.img_back)
    void btnBack(){
        finish();
    }

    @OnClick(R.id.tv_foeget_pwd)
    void clickForgetPwd(){
        startActivity(new Intent(this, ForgetPwdActivity.class));
        finish();
    }

    @OnClick(R.id.btn_login)
    void clickLogin(){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        Toast.makeText(this,"login / sign in",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_register)
    void clickSignUp(){
        startActivity(new Intent(this, SignUpActivity.class));// 跳转到注册页面
        finish();
    }
}
