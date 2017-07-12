package cn.datamining.dat.ui.login;

import cn.datamining.dat.di.PerActivity;
import cn.datamining.dat.di.component.ApplicationComponent;
import cn.datamining.dat.di.module.ActivityModule;
import dagger.Component;

/**
 * Created by wushange on 2017/7/12.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface LoginComponent {
    void inject(LoginActivity activity);
}
