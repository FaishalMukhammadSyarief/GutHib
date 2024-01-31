package com.zhalz.guthib.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.PagerAdapter
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModels()

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

        name?.let { viewModel.getDetailUser(it) }

        /* === SET DATA === */

        viewModel.userData.observe(this) { detailUser ->
            detailUser.name.let { binding.tvName.text = it }
            detailUser.bio.let { binding.tvBio.text = it }
        }

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