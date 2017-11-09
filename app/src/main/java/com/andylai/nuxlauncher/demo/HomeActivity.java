package com.andylai.nuxlauncher.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.andylai.nuxlauncher.R;

public class HomeActivity extends AppCompatActivity {


    private final String TAG = "MainActivity";

    Button mLauncherListDemo;
    Button mCoverFlowDemo;
    Button mSwippingPieMenuDemo;
    Button mSwippingViewPagerDemo;
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

        mSwippingViewPagerDemo = (Button) findViewById(R.id.swipping_view_pager);
        mSwippingViewPagerDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SwippingViewPagerDemoActivity.class));

            }
        });


    }









}
