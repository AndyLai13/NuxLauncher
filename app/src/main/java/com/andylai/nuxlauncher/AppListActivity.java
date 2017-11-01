package com.andylai.nuxlauncher;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
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
    PackageManager mPackageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);

        mPackageManager = getPackageManager();


        ListView AppList = (ListView) findViewById(R.id.app_list_view);
        ListAdapter adapter = new ArrayAdapter<>(this ,
                android.R.layout.simple_list_item_1 ,queryAllApps());
        AppList.setAdapter(adapter);

//int[] data = {1,2,3,4,5};
//
//List list = Arrays.asList(data);

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

        List<ResolveInfo> rList = mPackageManager.
                queryIntentActivities(mainIntent, 0);
        List<HomeInfo> homeList = new ArrayList<HomeInfo>();

        for(ResolveInfo resolveInfo : rList) {
            Log.d("Andy", "resolveInfo = " + resolveInfo);
            homeList.add(new HomeInfo(resolveInfo, mPackageManager));

        }
        return homeList;
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        for (ApplicationInfo info : list) {
            try {
                if (null != mPackageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }


    private ArrayList<String> queryAllApps() {

        ArrayList<String> AllApps = new ArrayList<>();
        String pkgAndClassName = null;

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfoList = mPackageManager.
                queryIntentActivities(intent, 0);
        for (ResolveInfo resolveInfo: resolveInfoList) {
            Log.d("Andy", "resolveInfo = " + resolveInfo.loadLabel(mPackageManager));
            Log.d("Andy", "toString = " + resolveInfo.toString());
            AllApps.add((String)resolveInfo.loadLabel(mPackageManager));
        }
        return AllApps;
    }
}

