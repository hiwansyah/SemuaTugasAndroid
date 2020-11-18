package com.example.jetpacknavigation.androidfakestoreex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpacknavigation.androidfakestoreex.model.Product
import com.example.jetpacknavigation.databinding.ItemProductsBinding

class ProductAdapter(private val context: Context, private val listener: ProductListener) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    interface ProductListener{
        fun onClick(product: Product)
    }
    private var product = listOf<Product>()
    fun setData(data: List<Product>) {
        product = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val viewHolder = ProductViewHolder(ItemProductsBinding.inflate(LayoutInflater.from(context), parent, false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindData(product[position])
    }

    private var list = listOf<Product>()
    inner class ProductViewHolder(private val binding: ItemProductsBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bindData(product: Product) {
            binding.tvTitle.text = product.title
            binding.tvPrice.text = "$ " + product.price.toString()
            Glide.with(binding.root).load(product.image).into(binding.ivImage)
            binding.btnBuy.setOnClickListener { listener.onClick(product) }
        }
    }

    override fun getItemCount(): Int = product.size
}