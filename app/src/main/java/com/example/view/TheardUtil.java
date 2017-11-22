package com.example.view;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/6/12.
 */
public class TheardUtil {

    private static Executor executor = Executors.newSingleThreadExecutor();
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void executeSubThread(Runnable runnable) {
        executor.execute(runnable);
    }

    public static void executeMainThread(Runnable runnable) {
        handler.post(runnable);
    }
}
