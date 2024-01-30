package com.zhalz.guthib.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zhalz.guthib.ui.followers.FollowersFragment
import com.zhalz.guthib.ui.following.FollowingFragment

class PagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when(position) {
            0 -> FollowersFragment()
            else -> FollowingFragment()
    }
}