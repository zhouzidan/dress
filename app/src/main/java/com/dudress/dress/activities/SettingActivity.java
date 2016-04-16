package com.dudress.dress.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dudress.dress.R;
import com.dudress.dress.util.AppUtil;

/**
 * Created by zhou on 16-4-1.
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.img_back).setOnClickListener(this);
        TextView versionName = (TextView) findViewById(R.id.tv_version_name);
        versionName.setText(AppUtil.getAppVersionName(this));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back:
                finish();
                break;
        }
    }

    public void showJianjie(View view){
        Toast.makeText(this,"jianjie",Toast.LENGTH_SHORT).show();
    }
    public void showWelcome(View view){
        Toast.makeText(this,"showWelcome",Toast.LENGTH_SHORT).show();
    }

    public void showProtocol(View view){
        Toast.makeText(this,"showProtocol",Toast.LENGTH_SHORT).show();
    }

    public void showWebsite(View view){
        Toast.makeText(this,"showWebsite",Toast.LENGTH_SHORT).show();
    }

}
