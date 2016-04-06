package com.dudress.dress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dudress.dress.setting.SettingActivity;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(this, GuideActivity.class));
        initVIew();
    }

    Fragment homeFragment = null;
    Fragment faxianFragment = null;
    Fragment ownerFragment = null;

    RadioGroup radioGroup;
    TextView titleTV;
    private void initVIew() {

    titleTV = (TextView) findViewById(R.id.tv_title);

        homeFragment = new HomeFragment();
        faxianFragment = new FaxianFragment();
        ownerFragment = new OwnerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content, homeFragment, "home")
                .add(R.id.main_content, faxianFragment, "faxian")
                .add(R.id.main_content, ownerFragment, "owner")
                .show(homeFragment)
                .hide(faxianFragment)
                .hide(ownerFragment).commit();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG, "checkId:" + checkedId);
                switch (checkedId) {
                    case R.id.radio_home:
                        Log.d(TAG, "radio_home is checked");
                        getSupportFragmentManager().beginTransaction().show(homeFragment)
                                .hide(faxianFragment)
                                .hide(ownerFragment).commit();
                        titleTV.setText(R.string.main_redio_btn_1);
                        break;
                    case R.id.radio_faxian:
                        getSupportFragmentManager().beginTransaction().hide(homeFragment)
                                .show(faxianFragment)
                                .hide(ownerFragment).commit();
                        titleTV.setText(R.string.main_redio_btn_2);
                        break;
                    case R.id.radio_owner:
                        Log.d(TAG, "radio_owner is checked");
                        getSupportFragmentManager().beginTransaction().hide(homeFragment)
                                .hide(faxianFragment)
                                .show(ownerFragment).commit();
                        titleTV.setText(R.string.main_redio_btn_3);
                        break;
                }

            }
        });
    }


    public void showSetting(View view) {
        startActivity(new Intent(this, SettingActivity.class));
    }
}
