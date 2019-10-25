package com.yto.network.interceptor;
import android.text.TextUtils;


import com.yto.network.INetworkRequestInfo;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/22 17:32
 * desc   :
 */
public class RequestInterceptor implements Interceptor {
    private INetworkRequestInfo mNetworkRequestInfo;
    public RequestInterceptor(INetworkRequestInfo networkRequestInfo) {
        this.mNetworkRequestInfo = networkRequestInfo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        if(mNetworkRequestInfo != null) {
            for(String key:mNetworkRequestInfo.getRequestHeaderMap().keySet()){
                if(!TextUtils.isEmpty(mNetworkRequestInfo.getRequestHeaderMap().get(key))) {
                    builder.addHeader(key, mNetworkRequestInfo.getRequestHeaderMap().get(key));
                }
            }
        }

        return chain.proceed(builder.build());
    }
}