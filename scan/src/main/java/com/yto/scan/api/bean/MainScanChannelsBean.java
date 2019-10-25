package com.yto.scan.api.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.yto.network.beans.BaseResponse;

import java.util.List;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/23 16:45
 * desc   :
 */
public class MainScanChannelsBean extends BaseResponse {
    @SerializedName("showapi_res_body")
    @Expose
    public ShowapiResBody showapiResBody;

    public class ChannelList {
        @SerializedName("channelId")
        @Expose
        public String channelId;
        @SerializedName("name")
        @Expose
        public String name;
    }

    public class ShowapiResBody {
        @SerializedName("totalNum")
        @Expose
        public Integer totalNum;
        @SerializedName("ret_code")
        @Expose
        public Integer retCode;
        @SerializedName("channelList")
        @Expose
        public List<ChannelList> channelList = null;
    }
}
