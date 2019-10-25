package com.yto.usercenter.fragment;


import com.yto.base.fragment.MvvmFragment;
import com.yto.base.viewmodel.MvvmBaseViewModel;
import com.yto.usercenter.R;
import com.yto.usercenter.databinding.FragmentMyCenterBinding;
import com.yto.usercenter.model.MyCenterViewModel;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/23 16:45
 * desc   :
 */
public class MyCenterFragment extends MvvmFragment<FragmentMyCenterBinding, MyCenterViewModel> {

    @Override
    public int getBindindVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my_center;
    }

    @Override
    public MyCenterViewModel getViewModel() {
        return new MyCenterViewModel();
    }

    @Override
    protected void onRetryBtnClick() {

    }

    @Override
    protected String getFragmentTag() {
        return null;
    }
}
