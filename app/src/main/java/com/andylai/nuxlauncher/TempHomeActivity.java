package com.andylai.nuxlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TempHomeActivity extends AppCompatActivity {

    Button mAppList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_home);

        mAppList = (Button) findViewById(R.id.app_list);
        mAppList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempHomeActivity.this, AppListActivity.class);
                startActivity(intent);
            }
        });

    }
}
