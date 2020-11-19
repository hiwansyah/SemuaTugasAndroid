package com.example.jetpacknavigation.fakeuserexersize.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpacknavigation.databinding.ItemUsersBinding
import com.example.jetpacknavigation.fakeuserexersize.model.User

class UserAdapter(private val context: Context, private val listener: UserListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    interface UserListener {
        fun onSelect(id: Int)
        fun onDelete(id: Int)
    }

    inner class ViewHolder(private val binding: ItemUsersBinding, private val listener: UserListener)
        : RecyclerView.ViewHolder(binding.root) {
        fun binData(item: User){
            binding.run {
                tvFirstname.text = item.firstName
                tvLastname.text = item.lastName
                tvEmail.text = item.email
                Glide.with(binding.root).load(item.avatar).into(ivAvatar)
                root.setOnClickListener{listener.onSelect(item.id)}
                btnAdd.setOnClickListener { listener.onDelete(item.id) }
            }
        }
    }

    var list = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    fun deleteProductById(id: Int){
        val index = list.indexOfFirst { it.id == id }
        if (index != -1){
            notifyItemRemoved(index)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        return ViewHolder(
            ItemUsersBinding.inflate(LayoutInflater.from(context), parent, false),listener
        )
    }



    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.binData(list[position])
    }

    override fun getItemCount(): Int = list.size
}