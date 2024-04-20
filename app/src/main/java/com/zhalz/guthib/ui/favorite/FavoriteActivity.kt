package com.zhalz.guthib.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.zhalz.guthib.R
import com.zhalz.guthib.adapter.UserAdapter
import com.zhalz.guthib.data.room.user.UserData
import com.zhalz.guthib.databinding.ActivityFavoriteBinding
import com.zhalz.guthib.ui.detail.DetailActivity
import com.zhalz.guthib.utils.Const
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {

    private val binding: ActivityFavoriteBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_favorite) }
    private val viewModel: FavoriteViewModel by viewModels()
    private val adapter: UserAdapter by lazy { UserAdapter{ toDetail(it) } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite)


        initUI()
        getListFav()

    }

    private fun getListFav() = lifecycleScope.launch {
        viewModel.getListFav().collect {
            adapter.submitList(it)
            binding.adapter = adapter
        }
    }

    private fun initUI() {
        /** == TOOLBAR == **/
        binding.toolbar.setNavigationOnClickListener { finish() }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun toDetail(data: UserData) {
        val toDetail = Intent(this, DetailActivity::class.java)
            .putExtra(Const.Parcel.EXTRA_USER, data)
        startActivity(toDetail)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getListFav()
    }
}