package com.gizmo.homelauncher

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gizmo.homelauncher.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), TextWatcher, onItemClickListener {
    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: AppListAdapter
    private lateinit var appList:ArrayList<HomeModel>
    private val appBR=AppBR()
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_home)
        viewModel=ViewModelProvider(this,HomeViewModelFactory(this)).get(HomeViewModel::class.java)
        appList=viewModel.getAppList()
        adapter= AppListAdapter(this, appList)
        binding.rvAppList.adapter=adapter
        binding.rvAppList.layoutManager=LinearLayoutManager(this)
        binding.edtSearch.addTextChangedListener(this)
        registerBR()
    }

    private fun registerBR() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED)
        intentFilter.addAction(Intent.ACTION_PACKAGE_FULLY_REMOVED)
        intentFilter.addDataScheme(getString(R.string.brpackage))
        registerReceiver(appBR, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(appBR)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
    override fun onTextChanged(charAppname: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
    override fun afterTextChanged(charAppname: Editable?) {
        val tempList=ArrayList<HomeModel>()
        for (homeModel in appList){
            if(homeModel.appName.toLowerCase().contains(charAppname.toString().toLowerCase())){
                tempList.add(homeModel)
            }
        }
        adapter.updateList(tempList)
    }

    override fun onClick(position: Int) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.component =
            ComponentName(appList[position].packageName, appList[position].className)
        startActivity(intent)
    }

}