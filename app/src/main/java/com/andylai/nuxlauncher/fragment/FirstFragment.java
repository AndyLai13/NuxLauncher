package com.andylai.nuxlauncher.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andylai.nuxlauncher.R;


public class FirstFragment extends ScaleFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Andy", "FirstFragment ; onCreateView");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }



}
