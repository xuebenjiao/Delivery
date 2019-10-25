package com.yto.scan.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.yto.scan.R;
import com.yto.scan.databinding.FragmentHeadlineNewsBinding;


/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/23 14:43
 * desc   :
 */
public class HeadLineNewsFragment extends Fragment {
    FragmentHeadlineNewsBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_headline_news, container, false);
        return mBinding.getRoot();
    }
}
