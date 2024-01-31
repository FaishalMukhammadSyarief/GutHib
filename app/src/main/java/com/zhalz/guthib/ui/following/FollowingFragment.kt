package com.zhalz.guthib.ui.following

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.FragmentFollowersBinding
import com.zhalz.guthib.ui.detail.DetailActivity

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)

        getFollowing()
        viewModel.listFollowing.observe(viewLifecycleOwner) { setRecycler(it) }

        return binding.root
    }

    fun getFollowing() {

        val followingData =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) activity?.intent?.getParcelableExtra(DetailActivity.EXTRA_USER, UserData::class.java)
            else activity?.intent?.getParcelableExtra(DetailActivity.EXTRA_USER)

        followingData?.login?.let { viewModel.getFollowing(it) }

    }

    private fun setRecycler(userList: List<UserData?>?) {
        val adapter = UserAdapter { toDetail(it) }
        adapter.submitList(userList)
        binding.rvFollowers.adapter = adapter
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