package com.gizmo.homelauncher

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel


class HomeViewModel(context: Context) : ViewModel() {
    private val context=context
    @RequiresApi(Build.VERSION_CODES.S)
    fun getAppList(): ArrayList<HomeModel> {
        val arrayList=ArrayList<HomeModel>()
        val applist : List<ApplicationInfo> = context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        for (appInfo in applist) {
            val packageInfo= context.packageManager.getPackageInfo(appInfo.packageName, PackageManager.GET_ACTIVITIES)
            if (context.packageManager.getLaunchIntentForPackage(packageInfo.packageName)!=null)
                arrayList.add(HomeModel(appInfo.loadLabel(context.packageManager).toString(),appInfo.packageName,appInfo.loadIcon(context.packageManager),context.packageManager.getLaunchIntentForPackage(packageInfo.packageName)!!.component?.className.toString(),packageInfo.versionCode.toString(),packageInfo.versionName))
        }
        arrayList.sortWith(compareBy { it.appName })
    return arrayList
    }


}