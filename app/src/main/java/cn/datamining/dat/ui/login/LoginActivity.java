package cn.datamining.dat.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.datamining.dat.MainActivity;
import cn.datamining.dat.R;
import cn.datamining.dat.base.BaseActivity;
import cn.datamining.dat.db.User;

/**
 * Created by wushange on 2017/7/12.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {
    @Inject
    LoginPresenter loginPresenter;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void initView(View view) {
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
        return "18519232094";
    }

    @Override
    public String getUserPassWord() {
        return "123456";
    }

    @Override
    public void showUser(User user) {
        ToastUtils.showLong(user.toString());
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        mOperation.forwardAndFinish(MainActivity.class, LEFT_RIGHT);
    }

}
