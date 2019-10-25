package com.yto.scan.model;

import com.google.gson.reflect.TypeToken;
import com.yto.base.model.BaseModel;
import com.yto.base.model.SuperBaseModel;
import com.yto.network.errorhandler.ExceptionHandle;
import com.yto.network.observer.BaseObserver;
import com.yto.scan.api.ScanApi;
import com.yto.scan.api.bean.MainScanChannelsBean;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/24 10:51
 * desc   :
 */
public class MainScanModel extends BaseModel<ArrayList<MainScanModel.Channel>> {
    private static final String PREF_KEY_HOME_CHANNEL = "pref_key_scan_channel";
    //apk 预制的缓存数据
    public static final String PREDEFINED_CHANNELS = "[\n" +
            "    {\n" +
            "        \"channelId\": \"5572a108b3cdc86cf39001cd\",\n" +
            "        \"channelName\": \"国内焦点\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"channelId\": \"5572a108b3cdc86cf39001ce\",\n" +
            "        \"channelName\": \"国际焦点\"\n" +
            "    }\n" +
            "]";

    @Override
    public void refresh() {

    }

    @Override
    protected void load() {
        ScanApi.getInstance().getNewsChannels(new BaseObserver<MainScanChannelsBean>(this) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                e.printStackTrace();
                loadFail(e.message);
            }

            @Override
            public void onNext(MainScanChannelsBean mainScanChannelsBean) {
                ArrayList<Channel> channels = new ArrayList<Channel>();
                for(MainScanChannelsBean.ChannelList source:mainScanChannelsBean.showapiResBody.channelList){
                    Channel channel = new Channel();
                    channel.channelId = source.channelId;
                    channel.channelName = source.name;
                    channels.add(channel);

                }
                loadSuccess(channels);
            }
        });
    }


    @Override
    protected String getCachedPreferenceKey() {
        //如果需要缓存在返回key,不需要在返回null，或者调用父类的
        return PREF_KEY_HOME_CHANNEL;
    }

    @Override
    protected Type getTClass() {
        return new TypeToken<ArrayList<Channel>>(){}.getType();
    }

    public class Channel{
        public String channelId;
        public String channelName;
    }
}
