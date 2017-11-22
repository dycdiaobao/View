package com.example.view;

import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Liang_Lu on 2017/5/30.
 */
public abstract class JsonStringCallBack extends Callback {


    @Override
    public Object parseNetworkResponse(Response response, int id) throws Exception {
        return response.body().string();
    }

    @Override
    public void onResponse(Object response, int id) {
        if (null==response.toString()&&"null".equals(response.toString())) {
            onFail("数据错误");
        } else {
            onSuccess(response.toString());
        }
    }


    @Override
    public void onError(Call call, Exception e, int id) {
        try{
            System.out.println(e.getMessage());
            onFail(e.getMessage());
        }catch (NullPointerException e1){
        }

    }

    public abstract void onSuccess(String result);

    public abstract void onFail(String error);
}
