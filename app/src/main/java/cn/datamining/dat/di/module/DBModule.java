package cn.datamining.dat.di.module;

import android.content.Context;

import javax.inject.Singleton;

import cn.datamining.dat.db.DaoMaster;
import cn.datamining.dat.db.DaoSession;
import cn.datamining.dat.db.MyOpenHelper;
import cn.datamining.dat.db.UserDao;
import dagger.Module;
import dagger.Provides;

/**
 * Created by sll on 2015/3/4.
 */
@Module
public class DBModule {

    @Provides
    @Singleton
    MyOpenHelper provideMyOpenHelper(Context context) {
        return new MyOpenHelper(context, "app.db", null);
    }

    @Provides
    @Singleton
    DaoMaster provideDaoMaster(MyOpenHelper helper) {
        return new DaoMaster(helper.getWritableDatabase());
    }

    @Provides
    @Singleton
    DaoSession provideDaoSession(DaoMaster master) {
        return master.newSession();
    }


    @Provides
    @Singleton
    UserDao getUserDao(DaoSession session) {
        return session.getUserDao();
    }

}
