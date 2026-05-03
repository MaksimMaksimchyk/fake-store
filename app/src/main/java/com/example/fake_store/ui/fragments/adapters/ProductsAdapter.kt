package com.example.fake_store.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fake_store.databinding.ItemProductBinding
import com.example.fake_store.domain.ProductModel

class ProductsAdapter(
    private val onProductClick: (ProductModel) -> Unit,
    private val currentUsdRate: Double
) :
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
            binding.productPrice.text =
                "$${product.price} (${"%.2f".format(product.price * currentUsdRate)} BYN)"
            Glide.with(binding.productImage).load(product.image).into(binding.productImage)
            binding.productCard.setOnClickListener {
                onProductClick(product)
            }
        }

    }

    fun updateList(newProducts: List<ProductModel>) {
        val diffResult = DiffUtil.calculateDiff(ProductsDiffUtilCallback(products, newProducts))
        products = newProducts
        diffResult.dispatchUpdatesTo(this)
    }

}