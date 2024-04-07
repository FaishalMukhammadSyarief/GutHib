package com.zhalz.guthib.ui.following

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
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.FragmentFollowingBinding
import com.zhalz.guthib.ui.detail.DetailActivity

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowingViewModel by viewModels()
    private val adapter by lazy { UserAdapter { toDetail(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_following, container, false)
        binding.adapter = adapter

        getFollowing()

        viewModel.listFollowing.observe(viewLifecycleOwner) { adapter.submitList(it) }
        viewModel.isLoading.observe(viewLifecycleOwner) { binding.isLoading = it}
        viewModel.isError.observe(viewLifecycleOwner) { binding.isError = it}
        viewModel.isEmpty.observe(viewLifecycleOwner) { binding.isEmpty = it}

        return binding.root
    }

    private fun getFollowing() {
        @Suppress("DEPRECATION")
        val followingData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) activity?.intent?.getParcelableExtra(DetailActivity.EXTRA_USER, UserData::class.java)
            else activity?.intent?.getParcelableExtra(DetailActivity.EXTRA_USER)

        followingData?.login?.let { viewModel.getFollowing(it) }
    }

    private fun toDetail(data: UserData) {
        val toDetail = Intent(requireActivity(), DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_USER, data)
        startActivity(toDetail)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}