package com.yto.base.fragment;

import com.yto.base.activity.IBaseView;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/23 16:16
 * desc   :
 */
public interface IBasePagingView extends IBaseView {
    void onLoadMoreFailure(String message);

    void onLoadMoreEmpty();
}
