package cn.datamining.dat.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import cn.datamining.dat.R;
import cn.datamining.dat.base.BaseFragmentV4;

/**
 * Created by wushange on 2017/7/19.
 */

public class HomeFragment extends BaseFragmentV4 {
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
