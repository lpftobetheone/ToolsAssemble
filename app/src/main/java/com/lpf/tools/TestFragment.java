package com.lpf.tools;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lpf.tools.adapter.MyRecyclerViewAdapter;
import com.lpf.tools.adapter.MyStaggeredViewAdapter;
import com.lpf.tools.base.BaseFragment;
import com.lpf.tools.ui.activity.ItemDetailActivity;
import com.lpf.tools.utils.SnackbarUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MyRecyclerViewAdapter.OnItemClickListener,MyStaggeredViewAdapter.OnItemClickListener{

    private View mView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyRecyclerViewAdapter mRecyclerViewAdapter;
    private MyStaggeredViewAdapter myStaggeredViewAdpater;

    private static final int VERTICAL_LIST = 0;
    private static final int HORIZONTAL_LIST = 1;
    private static final int VERTICAL_GRID = 2;
    private static final int HORIZONTAL_GRID = 3;
    private static final int STAGGERED_GRID = 4;

    private static final int SPAN_COUNT = 2;

    private int flag = 0;

    private Context mContext;


    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return mView = inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mContext = getActivity();

        mSwipeRefreshLayout = (SwipeRefreshLayout) mView.findViewById(R.id.id_swiperefreshlayout);
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_recyclerview);

        flag = (int) getArguments().get("flag");
        configRecyclerView();

        mSwipeRefreshLayout.setColorSchemeResources(R.color.main_blue_light, R.color.main_blue_dark);
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }

    private void configRecyclerView() {
        switch (flag) {
            case VERTICAL_LIST:
                mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
                break;
            case HORIZONTAL_LIST:
                mLayoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
                break;
            case VERTICAL_GRID:
                mLayoutManager = new GridLayoutManager(mContext,SPAN_COUNT,GridLayoutManager.VERTICAL,false);
                break;
            case HORIZONTAL_GRID:
                mLayoutManager = new GridLayoutManager(mContext,SPAN_COUNT,GridLayoutManager.HORIZONTAL,false);
                break;
            case STAGGERED_GRID:
                mLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
                break;
        }

        if(flag != STAGGERED_GRID){
            mRecyclerViewAdapter = new MyRecyclerViewAdapter(mContext);
            mRecyclerViewAdapter.setOnItemClickListener(this);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
        }else{
            myStaggeredViewAdpater = new MyStaggeredViewAdapter(mContext);
            myStaggeredViewAdpater.setOnItemClickListener(this);
            mRecyclerView.setAdapter(myStaggeredViewAdpater);
        }

        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                int temp = (int)(Math.random()*10);
                if(flag != STAGGERED_GRID){
                    mRecyclerViewAdapter.mDatas.add(0,"new Data"+temp);
                    mRecyclerViewAdapter.notifyDataSetChanged();
                }else{
                    myStaggeredViewAdpater.mDatas.add(0,"new Data"+temp);
                    myStaggeredViewAdpater.mHeights.add(0,(int)(Math.random()*200+300));
                    myStaggeredViewAdpater.notifyDataSetChanged();
                }
            }
        },1000);
    }

    @Override
    public void onItemClick(View view, int position) {
        SnackbarUtil.show(mRecyclerView,"单击"+position,0);

        Intent intent = new Intent(mContext, ItemDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        SnackbarUtil.show(mRecyclerView,"双击"+position,0);
    }
}
