package cn.datamining.dat;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.datamining.dat.base.BaseActivity;
import cn.datamining.dat.base.BaseFragmentV4;
import cn.datamining.dat.ui.home.HomeFragment;


/**
 * 首页
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ViewPagerAdapter adapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public void initView(View view) {
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        tablayout.setSelectedTabIndicatorHeight(0);
        ViewCompat.setElevation(tablayout, 10);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.newInstance(), "首页");
        adapter.addFragment(HomeFragment.newInstance(), "设置");
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    public void initInjector() {
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<BaseFragmentV4> mFragmentList = new ArrayList<BaseFragmentV4>();
        private final List<String> mFragmentTitleList = new ArrayList<String>();
        public ViewPagerAdapter(FragmentManager fm) { super(fm); }
        @Override
        public BaseFragmentV4 getItem(int position) { return mFragmentList.get(position);}
        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        public void addFragment(BaseFragmentV4 fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
    }

}
