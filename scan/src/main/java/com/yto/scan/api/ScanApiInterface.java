package com.yto.scan.api;




import com.yto.scan.api.bean.MainScanChannelsBean;
import com.yto.scan.api.bean.MainScanListBean;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;
import io.reactivex.Observable;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public interface ScanApiInterface {
    @GET("release/news")
    Observable<MainScanListBean> getNewsList(
            @Header("Source") String source,
            @Header("Authorization") String authorization,
            @Header("Date") String date,
            @QueryMap Map<String, String> options);

    @GET("release/channel")
    Observable<MainScanChannelsBean> getNewsChannels(
            @Header("Source") String source,
            @Header("Authorization") String authorization,
            @Header("Date") String date,
            @QueryMap Map<String, String> options);
}
