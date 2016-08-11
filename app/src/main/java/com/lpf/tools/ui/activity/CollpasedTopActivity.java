package com.lpf.tools.ui.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lpf.tools.R;
import com.lpf.tools.TestFragment;
import com.lpf.tools.adapter.MyViewPagerAdapter;
import com.lpf.tools.base.BaseActivity;
import com.lpf.tools.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 顶部图片收缩效果
 */
public class CollpasedTopActivity extends BaseActivity implements ViewPager.OnPageChangeListener
{

    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private String[] mTitles;
    private List<Fragment> mFragments;

    private MyViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collpased_top);

        initViews();

        initDatas();

        configViews();

        initListeners();
    }


    private void initViews() {
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_coordinatorlayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.id_appbarlayout);
        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
    }

    private void configViews() {

        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//      mToolBar.setOnMenuItemClickListener(onMenuItemClickListener);

        mViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(this);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mViewPagerAdapter);

    }


    private void initDatas() {

        mTitles = getResources().getStringArray(R.array.tab_titles);

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            Bundle mBundle = new Bundle();
            mBundle.putInt("flag", i);
            TestFragment mFragment = new TestFragment();
            mFragment.setArguments(mBundle);
            mFragments.add(mFragment);
        }

    }

    private void initListeners() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_floatingactionbutton:
                SnackbarUtil.show(v, "+1", 0);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mToolBar.setTitle(mTitles[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_navigation,menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            String msg = "";
            switch (item.getItemId()){
                case R.id.action_settings:
                    msg += "one";
                    break;
                case R.id.action_settings2:
                    msg += "two";
                    break;
                case R.id.action_settings3:
                    msg += "third";
                    break;
            }
            if(!TextUtils.isEmpty(msg)){
                SnackbarUtil.show(mViewPager,msg,0);
            }
            return true;
        }
    };
}
