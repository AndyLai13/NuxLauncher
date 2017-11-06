package com.andylai.nuxlauncher;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.andylai.nuxlauncher.demo.FirstFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AndyLai on 2017/11/6.
 */

public class CoverFlowFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragmentList = new ArrayList<>();

    public CoverFlowFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList.add(new FirstFragment());
        mFragmentList.add(new SecondFragment());
    }

//    public CoverFlowFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList) {
//        super(fm);
//        this.mFragmentList = mFragmentList;
//    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
