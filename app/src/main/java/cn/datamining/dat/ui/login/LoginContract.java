package cn.datamining.dat.ui.login;


import cn.datamining.dat.base.mvpbase.BaseView;
import cn.datamining.dat.bean.User;

/**
 * Created by sll on 2016/5/11.
 */
public interface LoginContract {
    interface LoginView extends BaseView {
        String getUserName();

        String getUserPassWord();

        void showUser(User user);
    }

    interface LoginPresenter {
        void login();

    }
}
