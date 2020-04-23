package com.massky.greenlandvland.app;

import android.content.Context;


import com.massky.greenlandvland.database.RealmHelper;
import com.massky.greenlandvland.di.component.AppComponent;
import com.massky.greenlandvland.di.component.DaggerAppComponent;
import com.massky.greenlandvland.di.module.AppModule;
import com.massky.greenlandvland.util.SettingUtil;
import com.squareup.leakcanary.LeakCanary;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDexApplication;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by chenxz on 2017/11/25.
 */

public class App extends MultiDexApplication {

    private static App instance;
    public static AppComponent appComponent = null;
    private static Context mContext;

    public static synchronized App getInstance() {
        return instance;
    }

    public static synchronized Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        init();
    }

    private void init() {
        initLeakCanary();
        initTheme();
        initDatabase();
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initDatabase() {
        Realm.init(this);
        // 使用默认配置
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(RealmHelper.DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void initTheme() {
        SettingUtil settingUtil = SettingUtil.getInstance();

        // 获取当前主题
        if (settingUtil.getIsNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .build();
        }
        return appComponent;
    }
}
