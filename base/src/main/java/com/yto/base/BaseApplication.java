package com.yto.base;

import android.app.Application;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/22 15:37
 * desc   :
 */
public class BaseApplication extends Application {
    //OOM won't happen
    public static Application sApplication;
    public static boolean sDebug;
//    BuildConfig 由于每个模块都有这个类，所以使用BuildConfig.DEBUG容易混乱
    public void setsDebug(boolean isDebug){
        sDebug = isDebug;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }
}
