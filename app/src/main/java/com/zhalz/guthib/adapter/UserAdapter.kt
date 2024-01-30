package com.zhalz.guthib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhalz.guthib.data.response.UserData
import com.zhalz.guthib.databinding.ItemUserBinding

class UserAdapter(val onItemClick : (UserData) -> Unit) : ListAdapter<UserData, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.tvName.text = getItem(position).login
        Glide
            .with(holder.itemView.context)
            .load(getItem(position).avatarUrl)
            .into(holder.binding.ivPhoto)
        holder.itemView.setOnClickListener { onItemClick(getItem(position)) }
    }

    inner class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserData>() {
            override fun areItemsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: UserData, newItem: UserData): Boolean {
                return oldItem == newItem
            }
        }
    }

}
