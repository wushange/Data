package cn.datamining.dat.api;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import cn.datamining.dat.Constants;
import cn.datamining.dat.components.retrofit.Callback;
import cn.datamining.dat.components.retrofit.ExceptionSubscriber;
import cn.datamining.dat.db.User;
import cn.datamining.dat.components.retrofit.BaseResponseFunc;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wushange on 2017/7/11.
 */

public class AccountApi {
    public static final String BASE_URL = Constants.HOST + "/m/usr/";
    private AccountApiService mAccountService;
    private Context mContext;

    public AccountApi(OkHttpClient mOkHttpClient, Context context) {
        this.mContext = context;
        Retrofit retrofit =
                new Retrofit.Builder()
                        .client(mOkHttpClient)
                        .baseUrl(BASE_URL)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        mAccountService = retrofit.create(AccountApiService.class);
    }

    public void login(String name, String passWord, Callback<User> simpleCallback) {
        mAccountService.login(name, passWord)
                .flatMap(new BaseResponseFunc<User>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<User>(simpleCallback, mContext));
    }

}
