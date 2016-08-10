package com.lpf.tools;

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
import android.view.MenuItem;
import android.view.View;

import com.lpf.tools.adapter.MyViewPagerAdapter;
import com.lpf.tools.base.BaseActivity;
import com.lpf.tools.utils.SnackbarUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private DrawerLayout mDrawerLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FloatingActionButton mFloatingActionButton;
    private NavigationView mNavigationView;

    private String[] mTitles;
    private List<Fragment> mFragments;

    private MyViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initDatas();

        configViews();

        initListeners();
    }


    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.id_coordinatorlayout);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.id_appbarlayout);
        mToolBar = (Toolbar) findViewById(R.id.id_toolbar);
        mTabLayout = (TabLayout) findViewById(R.id.id_tablayout);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.id_floatingactionbutton);
        mNavigationView = (NavigationView) findViewById(R.id.id_navigationview);
    }

    private void configViews() {

        setSupportActionBar(mToolBar);

        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.toggle_open, R.string.toggle_close);
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        mNavigationView.inflateHeaderView(R.layout.nav_header_drawer_navigation);
        mNavigationView.inflateMenu(R.menu.drawer_navigation);

        onNavgationViewMenuItemSelected(mNavigationView);

        mViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), mTitles, mFragments);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(this);

        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mViewPagerAdapter);

        mFloatingActionButton.setOnClickListener(this);

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

    private void onNavgationViewMenuItemSelected(NavigationView mNavigationView) {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            String msgStr = "";

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_camera:
                        msgStr = (String) item.getTitle();
                        break;
                    case R.id.nav_gallery:
                        msgStr = (String) item.getTitle();
                        break;
                    case R.id.nav_manage:
                        msgStr = (String) item.getTitle();
                        break;
                    case R.id.nav_send:
                        msgStr = (String) item.getTitle();
                        break;
                    case R.id.nav_share:
                        msgStr = (String) item.getTitle();
                        break;
                    case R.id.nav_slideshow:
                        msgStr = (String) item.getTitle();
                        break;
                }

                item.setChecked(true);
                mDrawerLayout.closeDrawers();

                SnackbarUtil.show(mViewPager,msgStr,0);

                return true;
            }
        });
    }
}
