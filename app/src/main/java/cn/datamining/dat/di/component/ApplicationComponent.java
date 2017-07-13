package cn.datamining.dat.di.component;

import android.content.Context;

import javax.inject.Singleton;

import cn.datamining.dat.MyApplication;
import cn.datamining.dat.api.AccountApi;
import cn.datamining.dat.base.BaseActivity;
import cn.datamining.dat.db.UserDao;
import cn.datamining.dat.di.module.ApiModule;
import cn.datamining.dat.di.module.ApplicationModule;
import cn.datamining.dat.di.module.DBModule;
import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, DBModule.class})
public interface ApplicationComponent {
    Context getContext();
    UserDao getUserDao();
    AccountApi getAccountApi();

    void inject(MyApplication mApplication);
    void inject(BaseActivity mBaseActivity);
}
