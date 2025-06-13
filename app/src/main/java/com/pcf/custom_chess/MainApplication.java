package com.pcf.custom_chess;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //todo if (BuildConfig.DEBUG)
        ARouter.openLog();  // 打开日志
        ARouter.openDebug(); // 开启调试模式
        ARouter.init(this);  // 初始化
    }

}
