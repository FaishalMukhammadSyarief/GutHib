package com.zhalz.guthib.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.ActivityMainBinding
import com.zhalz.guthib.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    private val adapter: UserAdapter by lazy { UserAdapter{ toDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.adapter = adapter

        initUI()
        viewModel.listUser.observe(this) { adapter.submitList(it) }

    }

    private fun initUI() {

        /** === SEARCH VIEW LISTENER === **/
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchUser(query ?: "")
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        /** === TOOLBAR MENU CLICK === **/
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_favorite -> {
                    Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                }
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

    private fun toDetail(data: UserData) {
        val toDetail = Intent(this, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_USER, data)

        startActivity(toDetail)
    }

}