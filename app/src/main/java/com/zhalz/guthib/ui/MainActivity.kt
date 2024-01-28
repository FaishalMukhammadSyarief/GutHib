package com.zhalz.guthib.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.ActivityMainBinding
import com.zhalz.guthib.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()

    }

    private fun initUI() {

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search_bar -> {
                    binding.searchView.show()
                    getUser()
                }
            }
            true
        }

    }

    private fun getUser() {
        viewModel.listUser.observe(this) {
            setQueryResult(it)
        }
    }

    private fun setQueryResult(userList: List<UserData?>?) {
        val adapter = UserAdapter()
        adapter.submitList(userList)
        binding.rvUser.adapter = adapter
    }

}