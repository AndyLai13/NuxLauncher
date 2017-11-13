package com.andylai.nuxlauncher;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.andylai.nuxlauncher.util.NuxUtil;

import java.util.List;

/**
 * Created by andy on 2017/11/1.
 */

public class CoverFlowAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    public static int sWidthPadding;
    public static int sHeightPadding;

    private List<View> mViewList;
    private OnPageSelectListener mListener;
    private Context mContext;

    public CoverFlowAdapter(List<View> mImageViewList, Context context) {
        mViewList = mImageViewList;
        mContext  = context;
        sWidthPadding  = NuxUtil.dpToPx(context, 24);
        sHeightPadding = NuxUtil.dpToPx(context, 32);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViewList.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        /*if (mViewList.size() > 0 && position < mViewList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            Log.i("info", "重新设置padding");
            Log.d("Andy", "position = " + position + ", positionOffset = " + positionOffset + ", position = " + position);
            int outHeightPadding = (int) (positionOffset * sHeightPadding);
            int outWidthPadding = (int) (positionOffset * sWidthPadding);
            // 从0滑动到一时，此时position = 0，其应该是缩小的，符合
            // position+1 为即将显示的页面，越来越大
            if (position < mViewList.size() - 1) {
                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding);
                int inHeightPadding = (int) ((1 - positionOffset) * sHeightPadding);
                mViewList.get(position + 1).setPadding(inWidthPadding, inHeightPadding, inWidthPadding, inHeightPadding);
            }
        }*/
        if (position < mViewList.size()) {


            if (positionOffset == 0) {
                mViewList.get(position).setTranslationX(0);
                mViewList.get(position).setScaleX(1.5f);
                mViewList.get(position).setScaleY(1.5f);
            } else {
                if ((position + 1) < mViewList.size()) {
                    mViewList.get(position + 1).setTranslationX(-10);

                    mViewList.get(position + 1).setScaleX(1.0f + 0.5f * positionOffset);
                    mViewList.get(position + 1).setScaleY(1.0f + 0.5f * positionOffset);
                }
                if ((position) >= 0) {
                    mViewList.get(position).setTranslationX(0);
                    mViewList.get(position).setScaleX(1.0f + 0.5f * (1 - positionOffset));
                    mViewList.get(position).setScaleY(1.0f + 0.5f * (1 - positionOffset));
                }

            }

        }


    }

    @Override
    public void onPageSelected(int position) {
        if (mListener != null) {
            mListener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    public void setOnPageSelectListener(OnPageSelectListener listener) {
        mListener = listener;
    }
}
