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

    private ViewPager mViewPagerTop;
    private ViewPager mViewPagerBot;
    private CoverFlowAdapter mAdapter;
    private OnPageSelectListener mListener;
    private List<View> mViewList = new ArrayList<>();
    private FragmentActivity mActivity;

//    private Button button1;
//    private Button button2;
//    private Button button3;

    CoverFlowFragmentPagerAdapter adapter;

    public CoverFlowViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.widget_cover_flow,this);
        mActivity = (FragmentActivity) context;
        init();
    }

    private void init() {
//        mViewPagerTop = (ViewPager) findViewById(R.id.top_cover_flow);

//        mAdapter = new CoverFlowAdapter(mViewList, getContext());
//        mAdapter.setOnPageSelectListener(this);
//
//        mViewPagerTop.setAdapter(mAdapter);
//        mViewPagerTop.addOnPageChangeListener(mAdapter);
//        mViewPagerTop.setOffscreenPageLimit(5);


//        button1 = (Button) findViewById(R.id.button);
//        button2 = (Button) findViewById(R.id.button11);
//        button3 = (Button) findViewById(R.id.button12);
//        button1.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewPagerBot.setCurrentItem(0, true);
//            }
//        });
//        button2.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewPagerBot.setCurrentItem(1, true);
//            }
//        });
//        button3.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mViewPagerBot.setCurrentItem(2, true);
//            }
//        });
        adapter = new CoverFlowFragmentPagerAdapter(
                mActivity.getSupportFragmentManager());

        mViewPagerBot = (ViewPager) findViewById(R.id.bot_cover_flow);

        mViewPagerBot.setAdapter(adapter);
        mViewPagerBot.addOnPageChangeListener(adapter);
        mViewPagerBot.setOffscreenPageLimit(5);


        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 传递给ViewPager 进行滑动处理
                return mViewPagerBot.dispatchTouchEvent(event);
            }
        });
    }

    public ViewPager getViewPager() {
        return mViewPagerBot;
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
//            layout.setPadding(
//                    CoverFlowAdapter.sWidthPadding, CoverFlowAdapter.sHeightPadding,
//                    CoverFlowAdapter.sWidthPadding, CoverFlowAdapter.sHeightPadding);
            layout.addView(view);
            mViewList.add(layout);
        }
        //mAdapter.notifyDataSetChanged();
    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        this.mListener = listener;
    }

    // callback
    @Override
    public void select(int position) {
        if(mListener!=null){
            mListener.select(position);
        }
    }


}
