package com.example.jetpacknavigation.fakeuserexersize.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.jetpacknavigation.R
import com.example.jetpacknavigation.databinding.FragmentUsersBinding
import com.example.jetpacknavigation.fakeuserexersize.adapter.UserAdapter
import com.example.jetpacknavigation.fakeuserexersize.model.DataItem
import com.example.jetpacknavigation.fakeuserexersize.model.User
import com.example.jetpacknavigation.fakeuserexersize.network.UserClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersFragment : Fragment(), UserAdapter.UserListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
                response.body()?.let { it -> adapter.list = it.data }
            }

            override fun onFailure(call: Call<DataItem>, t: Throwable) {
                onError(t)
            }
        })

        return binding.root
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()

        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsersFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onSelect(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onDelete(id: Int) {
        TODO("Not yet implemented")
    }
}