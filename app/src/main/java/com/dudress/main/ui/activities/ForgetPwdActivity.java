package com.dudress.main.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.dudress.main.R;

/**
 * Created by zhou on 16/3/13.
 */
public class ForgetPwdActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        findViewById(R.id.img_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_back:
            case R.id.tv_back:
                finish();
            break;

        }
    }
}
