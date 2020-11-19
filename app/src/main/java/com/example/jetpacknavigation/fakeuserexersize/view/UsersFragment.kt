package com.example.jetpacknavigation.fakeuserexersize.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.jetpacknavigation.databinding.FragmentUsersBinding
import com.example.jetpacknavigation.fakeuserexersize.adapter.UserAdapter
import com.example.jetpacknavigation.fakeuserexersize.model.DataItem
import com.example.jetpacknavigation.fakeuserexersize.model.User
import com.example.jetpacknavigation.fakeuserexersize.network.UserClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersFragment : Fragment(), UserAdapter.UserListener {
 
    private lateinit var binding: FragmentUsersBinding
    private val adapter by lazy { UserAdapter(requireContext(), this) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false).apply {
        rvUsers.adapter = adapter
        fabAdd.setOnClickListener {  }
    }

        UserClient.service.service.getAllUser().enqueue(object : Callback<DataItem> {
            override fun onResponse(
                call: Call<DataItem>,
                response: Response<DataItem>
            ) {
                response.body()?.let { adapter.list = it.data }
                binding.pgbar.visibility = View.GONE
            }

            override fun onFailure(call: Call<DataItem>, t: Throwable) {
                onError(t)
                binding.pgbar.visibility = View.VISIBLE
            }
        })

        return binding.root
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()

        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }
    

    override fun onSelect(id: Int) {
        toProductLayout(id)
    }

    private fun toProductLayout(id: Int = 0) {
        findNavController().navigate(
            UsersFragmentDirections.actionUsersFragmentToUserFragment(
                id
            )
        )
    }

    override fun onDelete(id: Int) {
        UserClient.service.service.deleteProductById(id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    adapter.deleteProductById(id)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onError(t)
            }
        })
    }
}