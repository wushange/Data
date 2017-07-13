package cn.datamining.dat.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.List;

import javax.inject.Inject;

import cn.datamining.dat.MainActivity;
import cn.datamining.dat.R;
import cn.datamining.dat.base.BaseActivity;
import cn.datamining.dat.bean.User;
import cn.datamining.dat.db.UserDao;

/**
 * Created by wushange on 2017/7/12.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {

    @Inject
    LoginPresenter loginPresenter;
    @Inject
    UserDao userDao;

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
            }
        });

    }

    @Override
    public void doBusiness(Context mContext) {
        loginPresenter.attachView(this);
        loginPresenter.login();
    }

    @Override
    public void initInjector() {
        DaggerLoginComponent.builder()
                .activityModule(getActivityModule())
                .applicationComponent(getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void startLoading() {
        showProgressDialog("登录...");
    }

    @Override
    public void endLoading() {
        dissmissProgressDialog();
    }

    @Override
    public String getUserName() {
        return "15645950466";
    }

    @Override
    public String getUserPassWord() {
        return "123456";
    }

    @Override
    public void showUser(User user) {
        LogUtils.e(user.toString());
        ToastUtils.showLong(user.toString());
       List<User> users=  userDao.queryBuilder().build().list();
        LogUtils.e("-db result-" + users.size() +"--" + users.toString());
    }
}
