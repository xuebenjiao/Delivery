package com.yto.base.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * author : xbj
 * e-mail : xbjzhu@163.com
 * date   : 2019/10/22 17:32
 * desc   :
 */
public class ToastUtil {
	private static Toast mToast;

	public static void show(Context context, String msg) {
		try {
			if (context != null && !TextUtils.isEmpty(msg)) {
				if(mToast != null){
					mToast.cancel();
				}
				mToast = Toast.makeText(context, "", Toast.LENGTH_LONG);
				mToast.setText(msg);
				mToast.show();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
