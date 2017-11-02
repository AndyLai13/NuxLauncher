package com.andylai.nuxlauncher.demo;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.andylai.nuxlauncher.AppListActivity;
import com.andylai.nuxlauncher.R;

public class LauncherListDemoActivity extends AppCompatActivity {

    private Button mAppList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_list_demo);
        mAppList = (Button) findViewById(R.id.app_list);
        mAppList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LauncherListDemoActivity.this,
                        AppListActivity.class));
            }
        });
    }
}
