package cn.datamining.dat.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.datamining.dat.MainActivity;
import cn.datamining.dat.R;
import cn.datamining.dat.base.BaseActivity;
import cn.datamining.dat.db.User;
import cn.datamining.dat.db.UserDao;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by wushange on 2017/7/12.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView ,RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener{

    @Inject
    LayoutInflater inflater;
    @Inject
    LoginPresenter loginPresenter;
    @Inject
    UserDao userDao;
    @BindView(R.id.rv_list)
    EasyRecyclerView recyclerView;

    @Inject
    UserListAdapter adapter;

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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                adapter.remove(position);
                return true;
            }
        });
        adapter.setMore(R.layout.user_item,this);
        recyclerView.setRefreshListener(this);
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
        List<User> users = userDao.queryBuilder().build().list();
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);

        adapter.addAll(users);
        LogUtils.e(adapter.getAllData().size() + user.toString());
        LogUtils.e("-db result-" + users.size() + "--" + users.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onRefresh() {

        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                adapter.addAll(adapter.getAllData());
            }
        });
    }

    @Override
    public void onLoadMore() {
        Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                adapter.addAll(adapter.getAllData());
            }
        });
    }
}
