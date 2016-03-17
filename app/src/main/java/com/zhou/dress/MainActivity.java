package com.zhou.dress;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends FragmentActivity {
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        initVIew();
    }

    Fragment homeFragment = null;
    Fragment faxianFragment = null;
    Fragment ownerFragment = null;

    RadioGroup radioGroup;

    private void initVIew() {
        homeFragment = new HomeFragment();
        faxianFragment = new FaxianFragment();
        ownerFragment = new OwnerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content, homeFragment,"home")
                .add(R.id.main_content, faxianFragment,"faxian")
                .add(R.id.main_content, ownerFragment,"owner")
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
                        break;
                    case R.id.radio_faxian:
                        getSupportFragmentManager().beginTransaction().hide(homeFragment)
                        .show(faxianFragment)
                        .hide(ownerFragment).commit();
                        break;
                    case R.id.radio_owner:
                        Log.d(TAG, "radio_owner is checked");
                        getSupportFragmentManager().beginTransaction().hide(homeFragment)
                        .hide(faxianFragment)
                        .show(ownerFragment).commit();
                        break;
                }

            }
        });
    }

}
