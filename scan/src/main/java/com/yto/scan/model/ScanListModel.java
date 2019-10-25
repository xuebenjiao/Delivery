package com.yto.scan.model;

import com.google.gson.reflect.TypeToken;
import com.yto.base.customview.BaseCustomViewModel;
import com.yto.base.model.BasePagingModel;
import com.yto.common.views.picturetitleview.PictureTitleViewViewModel;
import com.yto.common.views.titleview.TitleViewViewModel;
import com.yto.network.errorhandler.ExceptionHandle;
import com.yto.network.observer.BaseObserver;
import com.yto.scan.api.ScanApi;
import com.yto.scan.api.bean.MainScanListBean;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/24 13:28
 * desc   :
 */
public class ScanListModel<T> extends BasePagingModel<ArrayList<BaseCustomViewModel>> {
    private static final String PREF_KEY_NEWS_CHANNEL = "pref_key_scan_list";
    private String mChannelId = "";
    private String mChannelName ="";
    @Override
    public void refresh() {
        isRefresh = true;
        load();
    }
    public ScanListModel(String channelId,String channelName){
        this.mChannelId  = channelId;
        this.mChannelName = channelName;
    }


    @Override
    protected void load() {
        ScanApi.getInstance().getNewsList(new BaseObserver<MainScanListBean>(this) {
            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                e.printStackTrace();
                loadFail(e.message, isRefresh);
            }

            @Override
            public void onNext(MainScanListBean newsChannelsBean) {
                // All observer run on main thread, no need to synchronize
                pageNumber = isRefresh ? 2 : pageNumber + 1;
                ArrayList<BaseCustomViewModel> baseViewModels = new ArrayList<>();

                for (MainScanListBean.Contentlist source : newsChannelsBean.showapiResBody.pagebean.contentlist) {
                    if (source.imageurls != null && source.imageurls.size() > 1) {
                        PictureTitleViewViewModel viewModel = new PictureTitleViewViewModel();
                        viewModel.avatarUrl = source.imageurls.get(0).url;
                        viewModel.link = source.link;
                        viewModel.title = source.title;
                        baseViewModels.add(viewModel);
                    } else {
                        TitleViewViewModel viewModel = new TitleViewViewModel();
                        viewModel.link = source.link;
                        viewModel.title = source.title;
                        baseViewModels.add(viewModel);
                    }
                }
                loadSuccess(baseViewModels, baseViewModels.size() == 0, isRefresh, baseViewModels.size() == 0);
            }
        }, mChannelId, mChannelName, String.valueOf(isRefresh ? 1 : pageNumber));

    }


    @Override
    protected Type getTClass() {
        return new TypeToken<ArrayList<PictureTitleViewViewModel>>(){}.getType();
    }

    @Override
    protected String getCachedPreferenceKey() {
        return PREF_KEY_NEWS_CHANNEL + mChannelId;
    }
}
