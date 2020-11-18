package com.example.jetpacknavigation.androidfakestoreex.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jetpacknavigation.androidfakestoreex.adapter.ProductAdapter
import com.example.jetpacknavigation.androidfakestoreex.model.Product
import com.example.jetpacknavigation.androidfakestoreex.network.ProductClient
import com.example.jetpacknavigation.databinding.ActivityEcommerceBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EcommerceActivity : AppCompatActivity(), ProductAdapter.ProductListener {

    private val binding by lazy { ActivityEcommerceBinding.inflate(layoutInflater) }
    private  val mAdapter by lazy { ProductAdapter(this, this) }
    private val carts by lazy { mutableListOf<Product>()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            rvProduct.adapter = mAdapter
            tvBadge.text = "0"
        }

        binding.rvProduct.adapter = mAdapter

        ProductClient.usersService.getAllproduct().enqueue(object : Callback<List<Product>> {
            override fun onResponse(
                    call: Call<List<Product>>,
                    response: Response<List<Product>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        mAdapter.setData(it)
//                        Log.e("TAG", "onResponse: ${Gson().toJson(response.body())}")
                    }
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onClick(product: Product) {
        val index = carts.indexOf(product)
        if (index != -1){
            carts[index].mount++
        } else {
            product.mount++
            carts.add(product)
        }
        val total = carts.map { it.mount }.reduce { acc, i -> acc + i }
        binding.tvBadge.text = total.toString()
    }
/*
    override fun onClick(product: Product) {
        carts.add(product)

        val total: Int = carts.map {it.mount}.reduce {acc, i -> acc + 1}
        binding.tvBadge.text = total.toString()
    }*/
}