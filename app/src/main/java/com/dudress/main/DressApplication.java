package com.dudress.main;

import android.app.Application;
import android.content.Context;

import com.dudress.main.util.ImageLoaderUtil;
import com.orm.SugarContext;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by zhou on 16-3-14.
 */
public class DressApplication extends Application {

    private static DressApplication mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
        CrashReport.initCrashReport(getApplicationContext(), "900024227", false);
        ImageLoaderUtil.loadConfig(this);
        SugarContext.init(this);
    }

    public static Context getAppContext(){
        return mcontext;
    }
}
