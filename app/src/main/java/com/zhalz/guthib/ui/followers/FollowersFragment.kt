package com.zhalz.guthib.ui.followers

import android.content.Intent
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

class FollowersFragment : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFollowersBinding.inflate(layoutInflater, container, false)

        getData()

        return binding.root
    }

    private fun getData() {
        viewModel.apply {
            getFollowers("FaishalMukhammadSyarief")
            listFollowers.observe(viewLifecycleOwner) {
                setRecycler(it)
            }
        }

    }

    private fun setRecycler(userList: List<UserData?>?) {
        val adapter = UserAdapter { toDetail(it) }
        adapter.submitList(userList)
        binding.rvFollowers.adapter = adapter
    }

    private fun toDetail(data: UserData) {
        val toDetail = Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra("name", data.login)
            putExtra("image", data.avatarUrl)
        }
        startActivity(toDetail)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}