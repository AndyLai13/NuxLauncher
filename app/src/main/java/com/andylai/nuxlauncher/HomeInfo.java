package com.andylai.nuxlauncher;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

/**
 * Created by AndyLai on 2017/10/28.
 */

public class HomeInfo {
    public String name;
    public String packageName;
    public String label;
    public Drawable icon;

    public HomeInfo(ResolveInfo resInfo, PackageManager pm) {
        name = resInfo.activityInfo.name;
        packageName = resInfo.activityInfo.packageName;
        label = resInfo.loadLabel(pm).toString();
        icon = resInfo.loadIcon(pm);
    }
}