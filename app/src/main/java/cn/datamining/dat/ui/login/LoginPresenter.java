package cn.datamining.dat.ui.login;

import javax.inject.Inject;

import cn.datamining.dat.api.AccountApi;
import cn.datamining.dat.base.mvpbase.BasePresenter;
import cn.datamining.dat.db.User;
import cn.datamining.dat.db.UserDao;
import cn.datamining.dat.Constants;
import cn.datamining.dat.components.retrofit.Callback;
import cn.datamining.dat.di.PerActivity;

/**
 * Created by wanglj on 16/7/4.
 */


@PerActivity
public class LoginPresenter extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {

    private AccountApi accountApi;
    private UserDao userDao;

    @Inject
    public LoginPresenter(AccountApi accountApi,UserDao userDao) {
        this.accountApi = accountApi;
        this.userDao = userDao;
    }

    @Override
    public void login() {
        accountApi.login(mView.getUserName(),  crypt(mView.getUserPassWord(), mView.getUserName() + Constants.APPSECRET), new Callback<User>() {
            @Override
            public void onStart() {

                mView.startLoading();
            }

            @Override
            public void onNext(User user) {
                mView.showUser(user);
                userDao.insertOrReplace(user);
            }

            @Override
            public void onComplete() {

                mView.endLoading();
            }
        });

    }

    /**
     * 加密处理 szBuf为要加密字符串 szKey为自定义key值
     */
    public static String crypt(String szBuf, String szKey) {
        int x, y;
        y = 0;
        String back_str = "";
        byte[] temp_szKey = szKey.getBytes();
        byte[] temp_szBuf = szBuf.getBytes();
        byte[] end = new byte[szBuf.length()];
        for (x = 0; x < temp_szBuf.length; x++) {
            end[x] = (byte) ((temp_szBuf[x] & 0xF0) + ((temp_szBuf[x] & 0x0F) ^ (temp_szKey[y] & 0x0F)));
            y++;
            if (y >= szKey.length())
                y = 0;
        }
        back_str = new String(end);
        return back_str;
    }
}
