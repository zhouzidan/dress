package com.dudress.dress;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by zhou on 16-3-14.
 */
public class DressApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900024227", false);
    }
}
