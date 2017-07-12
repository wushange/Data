package cn.datamining.dat;

import cn.datamining.dat.base.BaseApplication;
import cn.datamining.dat.di.component.ApplicationComponent;
import cn.datamining.dat.di.component.DaggerApplicationComponent;
import cn.datamining.dat.di.module.ApplicationModule;

/**
 * Created by wushange on 2017/7/11.
 */

public class MyApplication extends BaseApplication {

    private ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    @Override
    public void exit() {

        removeAllActivity();
    }

    private void initComponent() {
        mApplicationComponent =
                DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
