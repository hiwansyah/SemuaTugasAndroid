package com.example.jetpacknavigation.fakeuserexersize.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.jetpacknavigation.databinding.FragmentUserBinding
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
 * Use the [UserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserFragment : Fragment() {
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

    private lateinit var binding: FragmentUserBinding
    private val args by navArgs<UserFragmentArgs>()
    private var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false).apply {
            /* if (args.id != 0) {
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
             }*/
         }

        return binding.root
    }

    /*private fun insertProduct(userModel: User) {
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
    }*/

    private fun onError(t: Throwable) {
        t.printStackTrace()

        Toast.makeText(requireContext(), t.message, Toast.LENGTH_SHORT).show()
    }

   /* private fun updateProduct(userModel: User) {
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
*/
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
