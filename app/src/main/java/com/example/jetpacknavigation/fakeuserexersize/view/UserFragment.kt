package com.example.jetpacknavigation.fakeuserexersize.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.jetpacknavigation.databinding.FragmentUserBinding
import com.example.jetpacknavigation.fakeuserexersize.model.User
import com.example.jetpacknavigation.fakeuserexersize.network.UserClient
import kotlinx.android.synthetic.main.item_users.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment : Fragment() {


    private lateinit var binding: FragmentUserBinding
    private val args by navArgs<UserFragmentArgs>()
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false).apply {
            if (args.id != 0) {
                 btnEdit.text = "UBAH PRODUK"

                 UserClient.service.service.getUserById(args.id).enqueue(object :
                     Callback<User> {
                     override fun onResponse(
                         call: Call<User>,
                         response: Response<User>
                     ) {
                         if (response.isSuccessful) {
                             response.body()?.let {
                                 user = it

                                 tieFirstname.setText(it.firstName)
                                 tieLastname.setText(it.lastName)
                                 tieEmail.setText(it.email.toString())

                                 if (it.avatar.isNotEmpty()) {
                                     Glide.with(requireActivity()).load(it.avatar).into(ivAvatar)
                                 }
                             }
                         }
                     }

                     override fun onFailure(call: Call<User>, t: Throwable) {
                         onError(t)
                     }
                 })

                 btnAdd.setOnClickListener {
                     user?.let {
                         updateProduct(
                             it.copy(
                                 firstName = tieFirstname.text.toString(),
                                 lastName = tieLastname.text.toString(),
                                 email = tieEmail.text.toString()
                             )
                         )
                     }
                 }
             } else {
                 btnEdit.setOnClickListener {
                     val userModel = User(
                         id = 0,
                         avatar = "",
                         firstName = tieFirstname.text.toString(),
                         lastName = tieLastname.text.toString(),
                         email = tieEmail.text.toString()
                     )

                     insertProduct(userModel)
                 }
             }
         }

        return binding.root
    }

    private fun insertProduct(userModel: User) {
        UserClient.service.service.insertUser(userModel).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) Toast.makeText(
                    requireActivity(),
                    "Produk berhasil ditambahkan",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onError(t)
            }
        })
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()

        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

   private fun updateProduct(userModel: User) {
        UserClient.service.service.updateUserById(userModel.id, userModel).enqueue(object :
            Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) Toast.makeText(
                    requireActivity(),
                    "Produk berhasil diubah",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onError(t)
            }
        })
    }

}
