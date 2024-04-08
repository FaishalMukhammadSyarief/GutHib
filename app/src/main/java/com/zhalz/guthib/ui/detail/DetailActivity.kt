package com.zhalz.guthib.ui.detail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.PagerAdapter
import com.zhalz.guthib.data.room.user.UserData
import com.zhalz.guthib.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initUI()
        getDetailUser()
        viewModel.isLoading.observe(this) { binding.animLoading.isVisible = it }

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

        @Suppress("DEPRECATION")
        val userData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) intent.getParcelableExtra(EXTRA_USER, UserData::class.java)
            else intent.getParcelableExtra(EXTRA_USER)

        userData?.login?.let { viewModel.getDetailUser(it) }

        viewModel.userData.observe(this) { detailUser ->
            detailUser.apply {
                login.let { binding.collapsingToolbar.title = it }
                name.let { binding.tvName.text = it }
                bio.let { binding.tvBio.text = it }
            }

            detailUser.avatarUrl.let {
                Glide
                    .with(this)
                    .load(it)
                    .into(binding.ivImage)
            }

            setFollowersTitle(detailUser.followers)
            setFollowingTitle(detailUser.following)
        }

    }

    private fun setFollowersTitle(totalFollowers: Int?) {
        val title = "FOLLOWERS  ($totalFollowers)"
        binding.tabLayout.getTabAt(0)?.text = title
    }

    private fun setFollowingTitle(totalFollowing: Int?) {
        val title = "FOLLOWING  ($totalFollowing)"
        binding.tabLayout.getTabAt(1)?.text = title
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }

}