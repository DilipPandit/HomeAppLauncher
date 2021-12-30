package com.gizmo.homelauncher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AppBR : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      if (intent!!.action.equals("android.intent.action.PACKAGE_FULLY_REMOVED")){
          Toast.makeText(context,context!!.getString(R.string.appinsalled),Toast.LENGTH_LONG).show()
      }else if (intent!!.action.equals("android.intent.action.PACKAGE_ADDED")){
          Toast.makeText(context,context!!.getString(R.string.appuninstalled),Toast.LENGTH_LONG).show()
      }
    }
}