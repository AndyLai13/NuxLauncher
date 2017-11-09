package com.andylai.nuxlauncher;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.andylai.nuxlauncher.fragment.FifthFragment;
import com.andylai.nuxlauncher.fragment.FirstFragment;
import com.andylai.nuxlauncher.fragment.FourthFragment;
import com.andylai.nuxlauncher.fragment.SecondFragment;
import com.andylai.nuxlauncher.fragment.ThirdFragment;
import com.andylai.nuxlauncher.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndyLai on 2017/11/6.
 */

public class CoverFlowFragmentPagerAdapter extends FragmentPagerAdapter implements
        ViewPager.OnPageChangeListener{

    List<Fragment> mFragmentList = new ArrayList<>();
    private OnPageSelectListener mListener;

    public CoverFlowFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList.add(new FirstFragment());
        mFragmentList.add(new SecondFragment());
        mFragmentList.add(new ThirdFragment());
        mFragmentList.add(new FourthFragment());
        mFragmentList.add(new FifthFragment());
    }

//    public CoverFlowFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
//        super(fm);
//        this.mFragmentList = mFragmentList;
//    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Log.d("Andy", "instantiateItem");
//        return instantiateItem(container, position);
//    }



    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d("Andy", "position = " + position + ", positionOffset = " + positionOffset + ", positionOffsetPixels = " + positionOffsetPixels);
        if (mFragmentList.size() > 0 && position < mFragmentList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            float max = Constant.SCALE_MAXIMUM;
            float min = Constant.SCALE_MINIMUM;
            float gap = max - min;
            float outScale = ( max - gap * positionOffset );
            float inScale = (gap * positionOffset + min);

            Log.d("Andy" ,"outScale = " + outScale);
            Log.d("Andy" ,"inScale  = " + inScale);
            // 从0滑动到一时，此时position = 0，其应该是缩小的，符合
            getItem(position).getView().setScaleX(outScale);
            getItem(position).getView().setScaleY(outScale);
            // position+1 为即将显示的页面，越来越大
            if (position < mFragmentList.size() - 1) {
                getItem(position + 1).getView().setScaleX(inScale);
                getItem(position + 1).getView().setScaleY(inScale);
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
}
