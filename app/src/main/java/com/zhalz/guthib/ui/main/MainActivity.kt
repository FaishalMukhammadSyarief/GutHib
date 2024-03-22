package com.zhalz.guthib.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.ActivityMainBinding
import com.zhalz.guthib.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
        getUser()
        viewModel.listUser.observe(this) { setRecycler(it) }

    }

    private fun initUI() {

        /* === TOOLBAR MENU CLICK === */
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search_bar -> {
                    binding.searchView.show()
                }
            }
            true
        }

        /* === MESSAGE === */
        viewModel.apply {
            isLoading.observe(this@MainActivity) { binding.isLoading = it }
            isError.observe(this@MainActivity) { binding.isError = it }
            isEmpty.observe(this@MainActivity) { binding.isEmpty = it }
        }

    }

    private fun getUser() {
        binding.searchView
            .editText
            .setOnEditorActionListener { _, _, _ ->
                val query = binding.searchView.text.toString()
                viewModel.getUser(query)

                val emptyList = listOf<UserData>()
                setRecycler(emptyList)

                binding.searchView.hide()
                true
            }
    }

    private fun setRecycler(userList: List<UserData?>?) {
        val adapter = UserAdapter{ toDetail(it) }
        adapter.submitList(userList)
        binding.adapter = adapter
    }

    private fun toDetail(data: UserData) {
        val toDetail = Intent(this, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_USER, data)

        startActivity(toDetail)
    }

}