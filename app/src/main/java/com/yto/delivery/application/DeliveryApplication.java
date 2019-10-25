package com.yto.delivery.application;

import android.app.Application;

import com.billy.cc.core.component.CC;
import com.kingja.loadsir.core.LoadSir;
import com.yto.base.BaseApplication;
import com.yto.base.loadsir.CustomCallback;
import com.yto.base.loadsir.ErrorCallback;
import com.yto.base.loadsir.LoadingCallback;
import com.yto.base.loadsir.TimeoutCallback;
import com.yto.delivery.BuildConfig;
import com.yto.network.ApiBase;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/22 15:32
 * desc   :
 */
public class DeliveryApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        setsDebug(BuildConfig.DEBUG);
        ApiBase.setNetworkRequestInfo(new NetworkRequestInfo());
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//添加各种状态页
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();
        CC.enableDebug(BuildConfig.DEBUG); // 默认是false: 关闭状态 debug模式下，会在Logcat中输出一些CC框架内部的执行日志，开启方式为：
        CC.enableVerboseLog(BuildConfig.DEBUG);	// 默认是false: 关闭状态 开启日志跟踪后，会在Logcat中输出CC调用的详细流程，将打印出每一个执行时的CC对象或CCResult对象的详细信息
        CC.enableRemoteCC(BuildConfig.DEBUG); // 默认是false: 关闭状态 开启跨app组件调用
    }
}
