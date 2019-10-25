package com.yto.scan.api;


import com.yto.network.ApiBase;
import com.yto.network.utils.TecentUtil;
import com.yto.scan.api.bean.MainScanChannelsBean;
import com.yto.scan.api.bean.MainScanListBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;

/**
 * Created by Allen on 2017/7/20.
 * 保留所有版权，未经允许请不要分享到互联网和其他人
 */
public final class ScanApi extends ApiBase {
    private static volatile ScanApi instance = null;
    private ScanApiInterface newsApiInterface;
    public static final String PAGE = "page";

    private ScanApi() {
        super("https://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/");
        newsApiInterface = retrofit.create(ScanApiInterface.class);
    }

    public static ScanApi getInstance() {
        if (instance == null) {
            synchronized (ScanApi.class) {
                if (instance == null) {
                    instance = new ScanApi();
                }
            }
        }
        return instance;
    }


    /**
     * 用于获取新闻栏目
     *
     * @param observer 由调用者传过来的观察者对象
     */
    public void getNewsChannels(Observer<MainScanChannelsBean> observer) {
        Map<String, String> requestMap = new HashMap<>();
        String timeStr = TecentUtil.getTimeStr();
        ApiSubscribe(newsApiInterface.getNewsChannels("source", TecentUtil.getAuthorization(timeStr), timeStr, requestMap), observer);
    }

    /**
     * 用于获取新闻列表
     *
     * @param observer    由调用者传过来的观察者对象
     * @param channelId   类型ID
     * @param channelName 获取页号
     * @param page        获取页号
     */
    public void getNewsList(Observer<MainScanListBean> observer, String channelId, String channelName, String page) {
        Map<String, String> requestMap = new HashMap<>();
        String timeStr = TecentUtil.getTimeStr();
        requestMap.put("channelId", channelId);
        requestMap.put("channelName", channelName);
        requestMap.put(PAGE, page);
        ApiSubscribe(newsApiInterface.getNewsList("source", TecentUtil.getAuthorization(timeStr), timeStr, requestMap), observer);
    }
}
