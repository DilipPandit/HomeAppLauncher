package com.gizmo.homelauncher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class AppListAdapter(onItemClickListener: onItemClickListener,private var appList: ArrayList<HomeModel>) : RecyclerView.Adapter<AppListAdapter.ViewHolder>() {
   val onItemClickListener=onItemClickListener
    class ViewHolder(private val viewItem: View) : RecyclerView.ViewHolder(viewItem){
        val tvAppName: TextView =viewItem.findViewById(R.id.tvAppName)
        val tvPackageName: TextView =viewItem.findViewById(R.id.tvPackageName)
        val tvClassName: TextView =viewItem.findViewById(R.id.tvClassName)
        val tvVersionCode: TextView =viewItem.findViewById(R.id.tvVersionCode)
        val tvVersionName: TextView =viewItem.findViewById(R.id.tvVersionName)
        val ivApp: ImageView =viewItem.findViewById(R.id.ivApp)
        val cvParent:CardView=viewItem.findViewById(R.id.cvParent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.custom_app_list,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
      return  appList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ivApp.setImageDrawable(appList[position].icon)
        holder.tvAppName.text= "App Name : ${appList[position].appName}"
        holder.tvClassName.text= "Class Name : ${appList[position].className}"
        holder.tvPackageName.text= "Package Name :${appList[position].packageName}"
        holder.tvVersionCode.text= "Version Code : ${appList[position].versionCode}"
        holder.tvVersionName.text= "Version Name : ${appList[position].versionName}"
        holder.cvParent.setOnClickListener {
            onItemClickListener.onClick(position)
        }
    }
    fun updateList(list : ArrayList<HomeModel>){
        appList=list
        notifyDataSetChanged()
    }
}