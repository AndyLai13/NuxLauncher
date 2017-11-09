package com.andylai.nuxlauncher.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andylai.nuxlauncher.R;


public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Andy", "SecondFragment ; onCreateView");
        return inflater.inflate(R.layout.fragment_second, container, false);
    }
}
