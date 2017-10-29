package com.andylai.nuxlauncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends AppCompatActivity {

    String[] app_name = {"app1", "app2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ListView AppList = (ListView) findViewById(R.id.app_list_view);
        ListAdapter adapter = new ArrayAdapter<>(this ,
                android.R.layout.simple_list_item_1 ,app_name);
        AppList.setAdapter(adapter);

        queryHomeApp();

//        adapter.addItemList(queryHomeApp());
//        lvHome.setAdapter(adapter);
//        lvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                HomeListItem item = (HomeListItem) adapter.getItem(position);
//                switchTo(item.getHomeInfo().packageName);
//            }
//        });
    }

    public void switchHome(String packageName) {
        Intent homeIntent = new Intent("android.intent.action.MAIN");
        homeIntent.addCategory("android.intent.category.HOME");
        homeIntent.addCategory("android.intent.category.DEFAULT");
        homeIntent.setPackage(packageName);
        startActivity(homeIntent);
        finish();
    }

    private List<HomeInfo> queryHomeApp() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_HOME);
        PackageManager pm = getPackageManager();
        List<ResolveInfo> rList = pm.queryIntentActivities(mainIntent, 0);
        List<HomeInfo> homeList = new ArrayList<HomeInfo>();

        for(ResolveInfo resolveInfo : rList) {
            Log.d("Andy", "resolveInfo = " + resolveInfo);
            homeList.add(new HomeInfo(resolveInfo, pm));

        }
        return homeList;
    }





}

