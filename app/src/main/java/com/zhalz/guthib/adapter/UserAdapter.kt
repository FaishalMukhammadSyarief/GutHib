package com.zhalz.guthib.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zhalz.guthib.data.User
import com.zhalz.guthib.databinding.ItemUserBinding

class UserAdapter (private val items: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.tvName.text = items[position].name
        holder.binding.tvFollowers.text = items[position].follower.toString()
        holder.binding.ivPhoto.setImageResource(items[position].photo)
    }

    inner class UserViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

}
