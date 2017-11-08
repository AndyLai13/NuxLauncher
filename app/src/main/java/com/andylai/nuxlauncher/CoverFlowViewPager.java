package com.andylai.nuxlauncher;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 2017/11/1.
 */

public class CoverFlowViewPager extends RelativeLayout implements OnPageSelectListener  {

    private ViewPager mViewPager;
    private CoverFlowAdapter mAdapter;
    private OnPageSelectListener mListener;
    private List<View> mViewList = new ArrayList<>();
    private FragmentActivity mActivity;

    public CoverFlowViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.widget_cover_flow,this);
        mActivity = (FragmentActivity) context;
        init();
    }

    private void init() {
        mViewPager = (ViewPager) findViewById(R.id.vp_conver_flow);

        mAdapter = new CoverFlowAdapter(mViewList, getContext());
        mAdapter.setOnPageSelectListener(this);


        CoverFlowFragmentPagerAdapter adapter = new CoverFlowFragmentPagerAdapter(
                mActivity.getSupportFragmentManager());


        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(adapter);
        mViewPager.setOffscreenPageLimit(5);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 传递给ViewPager 进行滑动处理
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }

    public void setViewList(List<View> viewList){

        /*
         * viewList must be init even if obtaining null list from other code,
         * in other words, it shall be given a set of initial values
         */
        if(viewList == null){
            //TODO: given initial values
            return;
        }
        mViewList.clear();
        for(View view: viewList){
            FrameLayout layout = new FrameLayout(getContext());
            layout.setPadding(
                    CoverFlowAdapter.sWidthPadding, CoverFlowAdapter.sHeightPadding,
                    CoverFlowAdapter.sWidthPadding, CoverFlowAdapter.sHeightPadding);
            layout.addView(view);
            mViewList.add(layout);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.mListener = listener;
    }

    // 显示的回调
    @Override
    public void select(int position) {
        if(mListener!=null){
            mListener.select(position);
        }
    }

}
