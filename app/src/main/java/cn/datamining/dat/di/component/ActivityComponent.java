package cn.datamining.dat.di.component;

import android.app.Activity;

import cn.datamining.dat.di.PerActivity;
import cn.datamining.dat.di.module.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  Activity getActivity();

}
