package com.dudress.dress.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dudress.dress.R;

/**
 * Created by zhou on 16/3/12.
 */
public class LoginActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);
        findViewById(R.id.layout_qq).setOnClickListener(this);
        findViewById(R.id.layout_sina).setOnClickListener(this);
        findViewById(R.id.layout_weixin).setOnClickListener(this);
        findViewById(R.id.tv_foeget_pwd).setOnClickListener(this);
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layout_qq:
                break;
            case R.id.layout_sina:
                break;
            case R.id.layout_weixin:
                break;
            case R.id.tv_foeget_pwd:
                startActivity(new Intent(this,ForgetPwdActivity.class));
                break;
            case R.id.img_back:
                finish();
                break;
        }
    }
}
