package com.yto.base.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.yto.base.loadsir.EmptyCallback;
import com.yto.base.loadsir.ErrorCallback;
import com.yto.base.loadsir.LoadingCallback;
import com.yto.base.viewmodel.MvvmBaseViewModel;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/22 17:14
 * desc   :
 */
public abstract class MvvmActivity<V extends ViewDataBinding,VM extends MvvmBaseViewModel> extends AppCompatActivity implements  IBaseView {
    private LoadService mLoadService;
    protected  VM viewModel;
    //界面句柄
    protected  V viewDatabinding;
    public abstract
    @LayoutRes//表示必须是一个Layout的资源id
    int getLayoutId();
    //数据结构需要子类去实现
    protected  abstract VM getViewModel();
    //获取界面绑定的参数
    public  abstract  int getBindingVariable();
    //点击提示信息的回调
    protected abstract void onRetryBtnClick();
    private void performDataBinding(){
        viewDatabinding = DataBindingUtil.setContentView(this,getLayoutId());
        if(viewModel == null){
            this.viewModel = getViewModel();
        }
        if(getBindingVariable() >0){
            //实现View和Data的绑定
            viewDatabinding.setVariable(getBindingVariable(),viewModel);
        }
        viewDatabinding.executePendingBindings();
    }

    /**
     * 在显示界面提示相关信息
     * @param view 需要提示信息的界面
     */
    public void setLoadSir(View view){
        //you can change  the callback on sub thread directly
        mLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onRetryBtnClick();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    @Override
    public void showContent() {
        if(mLoadService != null){
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
            mLoadService.showCallback(ErrorCallback.class);
        }
    }
}
