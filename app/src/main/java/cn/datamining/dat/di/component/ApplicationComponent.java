package cn.datamining.dat.di.component;

import android.content.Context;

import javax.inject.Singleton;

import cn.datamining.dat.MyApplication;
import cn.datamining.dat.base.BaseActivity;
import cn.datamining.dat.data.remote.AccountApi;
import cn.datamining.dat.di.module.ApiModule;
import cn.datamining.dat.di.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {
    Context getContext();

    AccountApi getAccountApi();
    void inject(MyApplication mApplication);

    void inject(BaseActivity mBaseActivity);
}
