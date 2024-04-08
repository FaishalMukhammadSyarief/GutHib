package com.zhalz.guthib.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.room.user.UserData
import com.zhalz.guthib.databinding.ActivityMainBinding
import com.zhalz.guthib.ui.setting.SettingActivity
import com.zhalz.guthib.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val adapter: UserAdapter by lazy { UserAdapter{ toDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.adapter = adapter

        initUI()
        observeTheme()
        viewModel.listUser.observe(this) { adapter.submitList(it) }

    }

    private fun initUI() {

        /** === SEARCH VIEW LISTENER === **/
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchUser(query ?: "")
                closeKeyboard(this@MainActivity)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        /** === TOOLBAR === **/
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_favorite -> Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
                R.id.menu_setting -> toSetting()
            }
            true
        }

        /** === MESSAGE === **/
        viewModel.apply {
            isLoading.observe(this@MainActivity) { binding.isLoading = it }
            isError.observe(this@MainActivity) { binding.isError = it }
            isEmpty.observe(this@MainActivity) { binding.isEmpty = it }
        }
    }

    private fun searchUser(query: String) {
        adapter.submitList(listOf<UserData>())
        viewModel.getUser(query)
    }

    fun closeKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun observeTheme() {
        viewModel.getTheme().observe(this) {
            when(it) {
                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    private fun toSetting() {
        val toDetail = Intent(this, SettingActivity::class.java)
        startActivity(toDetail)
    }

    private fun toDetail(data: UserData) {
        val toDetail = Intent(this, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_USER, data)
        startActivity(toDetail)
    }

}