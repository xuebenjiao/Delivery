package com.yto.delivery.application;



import com.yto.delivery.BuildConfig;
import com.yto.network.INetworkRequestInfo;

import java.util.HashMap;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/22 17:32
 * desc   :
 */
public class NetworkRequestInfo implements INetworkRequestInfo {
    HashMap<String, String> headerMap = new HashMap<>();

    public NetworkRequestInfo(){
        headerMap.put("os", "android");
        headerMap.put("versionName", BuildConfig.VERSION_NAME);
        headerMap.put("versionCode", String.valueOf(BuildConfig.VERSION_CODE));
        headerMap.put("applicationId", BuildConfig.APPLICATION_ID);
    }

    @Override
    public HashMap<String, String> getRequestHeaderMap() {
        return headerMap;
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
