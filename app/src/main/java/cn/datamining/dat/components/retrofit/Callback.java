package cn.datamining.dat.components.retrofit;

/**
 * Created by wanglj on 16/7/4.
 */

public interface Callback<T> {
    void onStart();
    void onNext(T t);
    void onComplete();
}
