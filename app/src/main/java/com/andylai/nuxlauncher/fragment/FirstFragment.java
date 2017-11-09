package com.andylai.nuxlauncher.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andylai.nuxlauncher.R;


public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("Andy", "FirstFragment ; onCreateView");
        return inflater.inflate(R.layout.fragment_first, container, false);
    }



}
