package com.yto.scan.model;

import com.yto.base.activity.IBaseView;
import com.yto.base.viewmodel.MvvmBaseViewModel;

import java.util.ArrayList;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/24 10:51
 * desc   :
 */
public class MainScanViewModel extends MvvmBaseViewModel<MainScanViewModel.IMainScanView,MainScanModel> {
    protected  interface IMainScanView extends IBaseView{
        void onChannelsLoaded(ArrayList<MainScanModel.Channel> list);
    }

}
