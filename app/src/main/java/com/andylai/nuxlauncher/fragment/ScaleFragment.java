package com.andylai.nuxlauncher.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.andylai.nuxlauncher.util.Constant;

/**
 * Created by AndyLai on 2017/11/9.
 */

public class ScaleFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        view.setScaleX(Constant.SCALE_MINIMUM);
        view.setScaleY(Constant.SCALE_MINIMUM);
    }
}
