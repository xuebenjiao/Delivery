package com.yto.scan.model;

import com.yto.base.activity.IBaseView;
import com.yto.base.customview.BaseCustomView;
import com.yto.base.customview.BaseCustomViewModel;
import com.yto.base.fragment.IBasePagingView;
import com.yto.base.model.BasePagingModel;
import com.yto.base.viewmodel.MvvmBaseViewModel;

import java.util.ArrayList;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/24 13:28
 * desc   :
 */
public class ScanListViewModel extends MvvmBaseViewModel<ScanListViewModel.IScanListView,ScanListModel> implements BasePagingModel.IModelListener<ArrayList<BaseCustomViewModel>> {
    private ArrayList<BaseCustomViewModel> mScanList = new ArrayList<BaseCustomViewModel>();
    public ScanListViewModel(String classId,String lboClassId){
        model = new ScanListModel(classId,lboClassId);
        model.register(this);
        model.getCachedDataAndLoad();
    }
    @Override
    public void onLoadFinish(BasePagingModel model, ArrayList<BaseCustomViewModel> data, boolean isEmpty, boolean isFirstPage, boolean hasNextPage) {

    }

    @Override
    public void onLoadFail(BasePagingModel model, String prompt, boolean isFirstPage) {

    }

    public interface IScanListView extends IBasePagingView {
        void onScansLoaded(ArrayList<BaseCustomView> channels);
    }
}
