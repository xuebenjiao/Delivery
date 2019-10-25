package com.yto.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yto.base.R;
import com.yto.base.activity.IBaseView;
import com.yto.base.loadsir.EmptyCallback;
import com.yto.base.loadsir.ErrorCallback;
import com.yto.base.loadsir.LoadingCallback;
import com.yto.base.utils.ToastUtil;
import com.yto.base.viewmodel.IMvvmBaseViewModel;
import com.yto.base.viewmodel.MvvmBaseViewModel;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/23 15:49
 * desc   :
 */
public abstract class MvvmFragment<V extends ViewDataBinding,VM extends IMvvmBaseViewModel> extends Fragment implements IBasePagingView {
    protected  VM viewModel;
    protected  V viewDataBinding;
    protected  String mFragmentTag = "";
    private LoadService mLoadService;
    private boolean isShowedContent = false;
    public abstract  int getBindindVariable();
    public  abstract
    @LayoutRes
    int getLayoutId();
    public abstract VM getViewModel();
    protected  abstract  void onRetryBtnClick();
    protected abstract String getFragmentTag();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParameters();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        return viewDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = getViewModel();
        if(viewModel != null  && view != null){
            viewModel.attachUI(this);//ViewModel 与UI进行绑定
        }
        if(getBindindVariable() >0){
            viewDataBinding.setVariable(getBindindVariable(),viewModel);
            viewDataBinding.executePendingBindings();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void showContent() {
        if(mLoadService != null){
            isShowedContent = true;
            mLoadService.showSuccess();
        }
    }

    @Override
    public void showLoading() {
        if(mLoadService != null){
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void onRefreshEmpty() {
        if(mLoadService != null) {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void onRefreshFailure(String message) {
        if(mLoadService != null){
            if(!isShowedContent){
                mLoadService.showCallback(ErrorCallback.class);
            }else{
//                ToastUtil.show(getContext(),message);
            }

        }
    }
    /**
     * 初始化参数 类似于Bundle等
     */
    protected  void initParameters(){

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(getContext());
        Log.d(getFragmentTag(), this + ": " + "onAttach");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (viewModel != null && viewModel.isUIAttached())
            viewModel.detachUI();//解绑
        Log.d(getFragmentTag(), this + ": " + "onDetach");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(getFragmentTag(), this + ": " + "onStop");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(getFragmentTag(), this + ": " + "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(getFragmentTag(), this + ": " + "onResume");
    }

    @Override
    public void onDestroy() {
        Log.d(getFragmentTag(), this + ": " + "onDestroy");
        super.onDestroy();
    }
    public void setLoadSir(View view) {
        // You can change the callback on sub thread directly.
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    @Override
    public void onLoadMoreFailure(String message) {
        ToastUtil.show(getContext(), message);
    }

    @Override
    public void onLoadMoreEmpty() {
        ToastUtil.show(getContext(), getString(R.string.no_more_data));
    }



}
