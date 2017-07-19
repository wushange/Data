package cn.datamining.dat.base;


import org.reactivestreams.Subscription;

/**
 * 创建presenter的时候 继承这个传入泛型View 主要做绑定view 和 一些初始化操作
 * Created by wushange on  2016/07/27.
 */
public abstract class BasePresenter<T> {
    public T mView;//View
    protected Subscription mSubscription;


    public void attachView(T view) {
        this.mView = view;
    }


    public void detachView() {
        mView = null;
    }

}
