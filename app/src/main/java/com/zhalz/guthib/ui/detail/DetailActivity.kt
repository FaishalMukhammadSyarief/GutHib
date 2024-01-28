package com.zhalz.guthib.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.PagerAdapter
import com.zhalz.guthib.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewPager.adapter = PagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.followers)
                1 -> tab.text = getString(R.string.following)
            }
        } .attach()
    }
}