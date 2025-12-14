package com.example.fake_store.ui.fragments.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fake_store.databinding.ItemProductBinding
import com.example.fake_store.domain.ProductModel

class ProductsAdapter() :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {
    private var products = listOf<ProductModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductModel) {
            binding.productTitle.text = product.title
            binding.productPrice.text = "$" + product.price.toString()
        }


    }

    fun updateList(newProducts: List<ProductModel>) {
        val diffResult = DiffUtil.calculateDiff(ProductsDiffUtilCallback(products, newProducts))
        products = newProducts
        diffResult.dispatchUpdatesTo(this)
    }

}