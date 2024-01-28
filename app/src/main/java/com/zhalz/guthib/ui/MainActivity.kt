package com.zhalz.guthib.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.User
import com.zhalz.guthib.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
        setRv()

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

    private fun setRv() {
        val list = listOf(
            User("faishal", 2, R.drawable.ic_github),
            User("faishal", 2, R.drawable.ic_github),
            User("faishal", 2, R.drawable.ic_github)
        )

        val adapter = UserAdapter()
        adapter.submitList(list)
        binding.rvUser.adapter = adapter
    }


}