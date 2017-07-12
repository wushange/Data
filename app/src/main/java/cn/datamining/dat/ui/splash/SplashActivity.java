package cn.datamining.dat.ui.splash;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;

import java.util.concurrent.TimeUnit;

import cn.datamining.dat.R;
import cn.datamining.dat.base.BaseActivity;
import cn.datamining.dat.ui.login.LoginActivity;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by wushange on 2017/7/12.
 */

public class SplashActivity extends BaseActivity {

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {

                LogUtils.e(aLong);
                mOperation.forwardAndFinish(LoginActivity.class,LEFT_RIGHT);
            }
        });
    }

    @Override
    public void initInjector() {

    }
}
