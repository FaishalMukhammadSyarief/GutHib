package com.zhalz.guthib.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.ActivityMainBinding
import com.zhalz.guthib.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
        getUser()
        startActivity(Intent(this, DetailActivity::class.java))


    }

    private fun initUI() {

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.search_bar -> {
                    binding.searchView.show()
                }
            }
            true
        }

    }

    private fun getUser() {

        binding.searchView
            .editText
            .setOnEditorActionListener { _, _, _ ->
                val query = binding.searchView.text.toString()
                viewModel.getUser(query)
                binding.searchView.hide()
                true
            }

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