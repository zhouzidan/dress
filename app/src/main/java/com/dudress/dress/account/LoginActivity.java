package com.dudress.dress.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dudress.dress.R;

/**
 * Created by zhou on 16/3/12.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    ImageView pwdVisibleImg;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.tv_foeget_pwd).setOnClickListener(this);
        findViewById(R.id.img_back).setOnClickListener(this);
        passwordEditText = (EditText) findViewById(R.id.ed_pwd);
        pwdVisibleImg = (ImageView) findViewById(R.id.img_pwd_visible);
        pwdVisibleImg.setOnClickListener(this);
        pwdVisibleImg.setTag(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_foeget_pwd:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_register:
                finish();
                startActivity(new Intent(this, SignUpActivity.class));// 跳转到注册页面
                break;
            case R.id.img_pwd_visible:
                boolean last_visible = (Boolean) pwdVisibleImg.getTag();
                pwdVisibleImg.setTag(!last_visible);
                if (last_visible) {
                    pwdVisibleImg.setImageResource(R.mipmap.hide_blue);
                    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    pwdVisibleImg.setImageResource(R.mipmap.display_blue);
                    passwordEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                passwordEditText.setSelection(passwordEditText.getText().toString().length());
                break;
        }
    }
}
