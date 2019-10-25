package com.yto.base.viewmodel;

import androidx.lifecycle.ViewModel;

import com.yto.base.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/22 17:20
 * desc   :
 */
public class MvvmBaseViewModel<V, M extends SuperBaseModel> extends ViewModel implements  IMvvmBaseViewModel<V> {
    private Reference<V> mUIRef;
    protected  M model;
    @Override
    public void attachUI(V ui) {
        mUIRef = new WeakReference<>(ui);
    }

    @Override
    public V getPageView() {
        if(mUIRef == null){
            return null;
        }
        return mUIRef.get();
    }

    @Override
    public boolean isUIAttached() {
        return mUIRef != null && mUIRef.get() != null;
    }

    @Override
    public void detachUI() {
        if(mUIRef != null){
            mUIRef.clear();
            mUIRef = null;
        }
        if(model != null){
            model.cancel();
        }

    }
}
