package com.andylai.nuxlauncher.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.andylai.nuxlauncher.AppListActivity;
import com.andylai.nuxlauncher.NuxUtil;
import com.andylai.nuxlauncher.OnSwipeTouchListener;
import com.andylai.nuxlauncher.PieLayout;
import com.andylai.nuxlauncher.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {


    private final String TAG = "MainActivity";

    Button mLauncherListDemo;
    Button mCoverFlowDemo;
    Button mSwippingPieMenuDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mLauncherListDemo = (Button) findViewById(R.id.launcher_list);
        mLauncherListDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LauncherListDemoActivity.class));

            }
        });
        mCoverFlowDemo = (Button) findViewById(R.id.cover_flow);
        mCoverFlowDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CoverFlowDemoActivity.class));
            }
        });
        mSwippingPieMenuDemo = (Button) findViewById(R.id.swipping_pie_menu);
        mSwippingPieMenuDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SwippingPieMenuDemoActivity.class));
            }
        });



    }









}
