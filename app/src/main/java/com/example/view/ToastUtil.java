package com.example.view;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * 
 * Toast工具类，防止Toast在show的时候重叠的现象
 * @Date Create at 2015年5月7日 上午11:53:47
 */
public class ToastUtil {

    private static Toast mToast;
    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };

	//持续时间
	private static int lastTime = 2 * 1000;

	public static void show(final String text, final Context context) {
		TheardUtil.executeMainThread(new Runnable() {
            @Override
            public void run() {
                if (mToast == null){
                    mToast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
                }else {
                    mToast.setText(text);
                }
                mToast.show();
            }
        });
	}
//	public static void showById(int textId) {
//		show(AppContext.mContext.getResources().getString(textId), lastTime);
//	}

	public static void show(String text,Context context, int time) {
	    mHandler.removeCallbacks(r);

        //空的字符串不弹出提示
        if(!TextUtils.isEmpty(text)) {
            if (mToast != null) {
                mToast.setText(text);
            } else {
                mToast = Toast.makeText(context, text, time);
                mHandler.postDelayed(r, 1000);
            }
            mToast.show();
        }
	}


}
