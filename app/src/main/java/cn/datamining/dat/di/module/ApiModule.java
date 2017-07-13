package cn.datamining.dat.di.module;

import android.content.Context;

import javax.inject.Singleton;

import cn.datamining.dat.api.AccountApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public AccountApi provideAccountApi( OkHttpClient okHttpClient,  Context mContext) {
        return new AccountApi(okHttpClient, mContext);
    }

}
