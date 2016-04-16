package com.dudress.dress;

import android.app.Application;
import android.content.Context;

import com.dudress.dress.db.models.Account;
import com.dudress.dress.db.models.Entry;
import com.dudress.dress.util.ImageLoaderUtil;
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
        Account.findById(Account.class,1);
        Entry.findById(Entry.class,1);
    }

    public static Context getAppContext(){
        return mcontext;
    }
}
