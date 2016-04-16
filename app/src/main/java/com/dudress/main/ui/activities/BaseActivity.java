package com.dudress.main.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

/**
 * Created by zhou on 16/4/8.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
