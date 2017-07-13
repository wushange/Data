package cn.datamining.dat.bean;


import com.blankj.utilcode.util.LogUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by wanglj on 16/7/4.
 */

public class BaseResponseFunc<T> implements Function<BaseResponse<T>, Observable<T>> {

    @Override
    public Observable<T> apply(@NonNull BaseResponse<T> tBaseResponse) throws Exception {
        LogUtils.e("server Response==>>"+ tBaseResponse.toString());
        //遇到非200错误统一处理,将BaseResponse转换成您想要的对象
        if (tBaseResponse.getResultCode() != 0) {
            return Observable.error(new Throwable(tBaseResponse.getMessage()));
        }else{
            return Observable.just(tBaseResponse.getData());
        }
    }
}
