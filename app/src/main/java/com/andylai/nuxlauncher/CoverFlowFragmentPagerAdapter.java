package com.andylai.nuxlauncher;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.andylai.nuxlauncher.fragment.FifthFragment;
import com.andylai.nuxlauncher.fragment.FirstFragment;
import com.andylai.nuxlauncher.fragment.FourthFragment;
import com.andylai.nuxlauncher.fragment.SecondFragment;
import com.andylai.nuxlauncher.fragment.ThirdFragment;

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
        if (positionOffset == 0) {
            getItem(position).getView().setScaleX(0.8f);
            getItem(position).getView().setScaleY(0.8f);
            getItem(position).getView().requestLayout();
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
