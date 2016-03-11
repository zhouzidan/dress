package com.zhou.dress;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends FragmentActivity {
private static  final String TAG = MainActivity.class.getName();
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
    private void initVIew(){
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,homeFragment).commit();
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                Log.d(TAG,"checkId:"+checkedId);
                switch (checkedId){
                    case R.id.radio_home:
                        Log.d(TAG,"radio_home is checked");
                        if (homeFragment == null)
                            homeFragment = new HomeFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, homeFragment).commit();
                        break;
                    case R.id.radio_faxian:
                        if (faxianFragment == null)
                            faxianFragment = new FaxianFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, faxianFragment).commit();
                        break;
                    case R.id.radio_owner:
                        Log.d(TAG,"radio_owner is checked");
                        if(ownerFragment == null)
                            ownerFragment = new OwnerFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_content, ownerFragment).commit();
                        break;
                }

            }
        });
    }

}
