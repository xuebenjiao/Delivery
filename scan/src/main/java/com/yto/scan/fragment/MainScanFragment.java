package com.yto.scan.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.yto.base.fragment.MvvmFragment;
import com.yto.scan.R;
import com.yto.scan.databinding.FragmentMainScanBinding;
import com.yto.scan.model.MainScanViewModel;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/24 10:51
 * desc   :
 */
public class MainScanFragment extends  MvvmFragment<FragmentMainScanBinding, MainScanViewModel> {
    @Override
    public int getBindindVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_scan;
    }

    @Override
    public MainScanViewModel getViewModel() {
        return new MainScanViewModel();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding.tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected String getFragmentTag() {
        return "MainScanFragment";
    }
}
