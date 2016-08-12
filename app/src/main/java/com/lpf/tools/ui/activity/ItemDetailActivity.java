package com.lpf.tools.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lpf.tools.R;
import com.lpf.tools.base.BaseActivity;

/**
 * RecyclerView的item点击事件 页面
 */
public class ItemDetailActivity extends BaseActivity {

    private CollapsingToolbarLayout mToolbarLayout;
    private Toolbar mToolbar;
    private ImageView mImgTop;
    private int nightMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        initViews();

    }

    private void initViews() {

        mToolbar = (Toolbar) this.findViewById(R.id.item_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbarLayout = (CollapsingToolbarLayout) this.findViewById(R.id.item_toolbarlayout);
        mToolbarLayout.setTitle("Detail Information");

        mImgTop = (ImageView) this.findViewById(R.id.item_img_top);
        loadBackdrop(mImgTop);

        //左上角 后退功能
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 加载详情界面头部图片
     *
     * @param imageview
     */
    private void loadBackdrop(ImageView imageview) {
        Glide.with(this).load(R.mipmap.ic_user_background).into(imageview);
    }


    /**
     * 设置Toolbar上的菜单按钮，选择应用白天还是黑夜Mode
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.day_night, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                menu.findItem(R.id.menu_night_mode_system).setCheckable(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                //如果有DrawerLayout,可以在这里打开左侧抽屉
                break;
            case R.id.menu_night_mode_system:
                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case R.id.menu_night_mode_day:
                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.menu_night_mode_night:
                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case R.id.menu_night_mode_auto:
                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置应用的主题样式
     * @param nightMode
     */
    public void setNightMode(int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);
        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }
}
