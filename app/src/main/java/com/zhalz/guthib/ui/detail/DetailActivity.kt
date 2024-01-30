package com.zhalz.guthib.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.PagerAdapter
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
        getDetailUser()

    }

    private fun initUI() {

        /* === VIEW PAGER === */
        binding.viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.followers)
                1 -> tab.text = getString(R.string.following)
            }
        } .attach()

        /* === TOOLBAR NAV CLICK === */
        binding.toolbar.setNavigationOnClickListener { finish() }

    }

    private fun getDetailUser() {

        /* === GET DATA === */
        @Suppress("DEPRECATION")
        val userData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.getParcelableExtra(EXTRA_USER, UserData::class.java)
            else intent.getParcelableExtra(EXTRA_USER)

        val name = userData?.login
        val image = userData?.avatarUrl

        /* === SET DATA === */
        binding.collapsingToolbar.title = name
        Glide
            .with(this)
            .load(image)
            .into(binding.ivImage)
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

}