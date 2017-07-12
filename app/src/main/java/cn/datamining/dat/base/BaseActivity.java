package cn.datamining.dat.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import cn.datamining.dat.MyApplication;
import cn.datamining.dat.di.component.ActivityComponent;
import cn.datamining.dat.di.component.ApplicationComponent;
import cn.datamining.dat.di.module.ActivityModule;
import cn.datamining.dat.widget.swipebacklayout.SwipeBackLayout;
import cn.datamining.dat.widget.swipebacklayout.Utils;
import cn.datamining.dat.widget.swipebacklayout.activity.SwipeBackActivityBase;
import cn.datamining.dat.widget.swipebacklayout.activity.SwipeBackActivityHelper;


/**
 * android 系统中的四大组件之一Activity基类
 *
 * @author 曾繁添
 * @version 1.0
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity, IBaseConstant, SwipeBackActivityBase {

    /*** 整个应用Applicaiton **/
    private MyApplication mApplication = null;
    /***当前Activity的弱引用，防止内存泄露**/
    private WeakReference<Activity> mContextWR = null;
    /*** 动画类型**/
    private int mAnimationType = NONE;
    /*** 是否运行截屏**/
    private boolean isCanScreenshot = true;
    private boolean autoDissIm = false;//是否自动检测点击屏幕边缘隐藏输入法
    /*** 共通操作**/
    protected Operation mOperation = null;
    /*** 日志输出标志**/
    protected final String TAG = this.getClass().getSimpleName();
    protected SwipeBackActivityHelper mHelper;
    protected ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "BaseActivity-->onCreate()");
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        // 获取应用Application
        mApplication = (MyApplication) getApplicationContext();
        initInjector();
        //需要在setContentView之前配置window的一些属性
        config(savedInstanceState);
        // 设置渲染视图View
        /*
      当前Activity渲染的视图View
     */
        ViewGroup mContextView = (ViewGroup) LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(mContextView);
        // 将当前Activity压入栈
        mContextWR = new WeakReference<Activity>(this);
        mApplication.pushActivity(mContextWR);
        // 实例化共通操作
        mOperation = new Operation(this);
        // 初始化参数
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mAnimationType = bundle.getInt(ANIMATION_TYPE, NONE);
        } else {
            bundle = new Bundle();
        }
        initParms(bundle);
        // 初始化控件
        initView(mContextView);
        // 业务操作
        doBusiness(this);
        // 是否可以截屏
        if (!isCanScreenshot) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }
    }

    @Override
    public void config(Bundle savedInstanceState) {

    }

    /**
     * 注入Injector
     */
    public abstract void initInjector();


    protected ApplicationComponent getApplicationComponent() {
        return ((MyApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity-->onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity-->onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity-->onResume()");
        resume();
    }

    @Override
    public void resume() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity-->onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "BaseActivity-->onStop()");
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "BaseActivity-->onDestroy()");
        destroy();
        mApplication.removeActivity(mContextWR);
        super.onDestroy();
    }

    @Override
    public void destroy() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //让Fragment可以消费
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 获取当前Activity
     *
     * @return
     */
    protected Activity getContext() {
        if (null != mContextWR)
            return mContextWR.get();
        else
            return null;
    }

    /**
     * 获取共通操作机能
     */
    public Operation getOperation() {
        return this.mOperation;
    }

    /**
     * 设置是否可截屏
     */
    public void setCanScreenshot(boolean isCanScreenshot) {
        this.isCanScreenshot = isCanScreenshot;
    }

    /**
     * 设置是否触摸关闭输入法
     *
     * @param autoDissIm
     */
    public void setAutoDissIm(boolean autoDissIm) {
        this.autoDissIm = autoDissIm;
    }

    /**
     * 引导状态存储偏好
     */
    protected final static String GUIDE_STATUS = "guide_status_sp";


    /**
     * 调用JNI底层实现获取本地图片资源
     *
     * @param mContext
     * @param resId
     * @return
     */
    public Bitmap readBitMap(Context mContext, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inJustDecodeBounds = false;
        // width，hight设为原来的十分一
        // opt.inSampleSize = 10;
        // 获取资源图片
        InputStream is = mContext.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void finish() {
        super.finish();
        int mAnimIn = 0;
        int mAnimOut = 0;
        switch (mAnimationType) {
            //左进右出
            case IBaseActivity.LEFT_RIGHT:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_left_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_slide_right_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            //上进下出
            case IBaseActivity.TOP_BOTTOM:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_up_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_push_bottom_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            case IBaseActivity.FADE_IN_OUT:
                mAnimIn = BaseView.gainResId(mApplication, BaseView.ANIM, "base_fade_in");
                mAnimOut = BaseView.gainResId(mApplication, BaseView.ANIM, "base_fade_out");
                overridePendingTransition(mAnimIn, mAnimOut);
                break;
            default:
                break;
        }
        mAnimationType = NONE;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (autoDissIm) {
            if (ev.getAction() == MotionEvent.ACTION_DOWN) {
                View v = getCurrentFocus();
                if (isShouldHideInput(v, ev)) {

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    }
                }
                return super.dispatchTouchEvent(ev);
            }
            if (getWindow().superDispatchTouchEvent(ev)) {
                return true;
            }

        } else {
            return super.dispatchTouchEvent(ev);
        }
        return onTouchEvent(ev);
    }

    public void setSwipeBackEnable(boolean enable, int flag) {
        getSwipeBackLayout().setEnableGesture(enable);
        getSwipeBackLayout().setEdgeTrackingEnabled(flag);
    }

    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
        getSwipeBackLayout().setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {

        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();

    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    private ProgressDialog progressDialog;
    protected void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void dissmissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
