package cn.datamining.dat.components.retrofit;


import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;


/**
 * 错误统一处理
 *
 * Created by wanglj on 16/7/4.
 */

public class ExceptionSubscriber<T> implements Observer<T> {


    private Callback<T> simpleCallback;
    private Context application;

    public ExceptionSubscriber(Callback simpleCallback, Context application){
        this.simpleCallback = simpleCallback;
        this.application = application;
    }

    @Override
    public void onComplete() {
        if(simpleCallback != null)
            simpleCallback.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof SocketTimeoutException) {
           Toast.makeText(application, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
           Toast.makeText(application, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
           Toast.makeText(application, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        if(simpleCallback != null)
            simpleCallback.onComplete();
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if(simpleCallback != null)
            simpleCallback.onStart();
    }

    @Override
    public void onNext(T t) {
        if(simpleCallback != null)
            simpleCallback.onNext(t);
    }
}
