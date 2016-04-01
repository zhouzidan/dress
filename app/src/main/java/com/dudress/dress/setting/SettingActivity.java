package com.dudress.dress.setting;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.Toast;

import com.dudress.dress.R;

/**
 * Created by zhou on 16-4-1.
 */
public class SettingActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.img_back).setOnClickListener(this);
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
