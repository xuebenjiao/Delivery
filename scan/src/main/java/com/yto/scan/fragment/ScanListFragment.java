package com.yto.scan.fragment;

import com.yto.base.fragment.MvvmFragment;
import com.yto.scan.R;
import com.yto.scan.databinding.ScanFragmentBinding;
import com.yto.scan.model.ScanListViewModel;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/24 13:26
 * desc   :
 */
public class ScanListFragment extends MvvmFragment<ScanFragmentBinding, ScanListViewModel> {
    @Override
    public int getBindindVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.scan_fragment;
    }

    @Override
    public ScanListViewModel getViewModel() {
        return new ScanListViewModel();
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected String getFragmentTag() {
        return "ScanListFragment";
    }
}
