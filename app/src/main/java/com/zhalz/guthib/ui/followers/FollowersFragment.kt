package com.zhalz.guthib.ui.followers

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.utils.Const.Parcel.EXTRA_USER
import com.zhalz.guthib.data.room.user.UserData
import com.zhalz.guthib.databinding.FragmentFollowersBinding
import com.zhalz.guthib.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowersViewModel by viewModels()
    private val adapter by lazy { UserAdapter { toDetail(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_followers, container, false)
        binding.adapter = adapter

        getFollowers()

        viewModel.listFollowers.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.isLoading.observe(viewLifecycleOwner) { binding.isLoading = it}
        viewModel.isError.observe(viewLifecycleOwner) { binding.isError = it}
        viewModel.isEmpty.observe(viewLifecycleOwner) { binding.isEmpty = it}

        return binding.root
    }

    private fun getFollowers() {
        @Suppress("DEPRECATION")
        val followersData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) activity?.intent?.getParcelableExtra(EXTRA_USER, UserData::class.java)
            else activity?.intent?.getParcelableExtra(EXTRA_USER)

        followersData?.login?.let { viewModel.getFollowers(it) }
    }

    private fun toDetail(data: UserData) {
        val toDetail = Intent(requireActivity(), DetailActivity::class.java)
            .putExtra(EXTRA_USER, data)
        startActivity(toDetail)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}