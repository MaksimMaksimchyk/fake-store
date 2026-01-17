package com.example.fake_store.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fake_store.databinding.ItemProductInCartBinding
import com.example.fake_store.domain.ProductInCartModel

class ProductsInCartAdapter(private val onProductClick: (ProductInCartModel) -> Unit) :
    RecyclerView.Adapter<ProductsInCartAdapter.ProductViewHolder>() {
    private var products = listOf<ProductInCartModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        val binding =
            ItemProductInCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class ProductViewHolder(private val binding: ItemProductInCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productInCart: ProductInCartModel) {
            binding.productTitle.text = productInCart.product.title
            binding.productPrice.text = "$" + productInCart.product.price.toString()
            Glide.with(binding.productImage).load(productInCart.product.image)
                .into(binding.productImage)
            binding.productQuantity.text = "Quantity: " + productInCart.quantity.toString()
            binding.deleteButton.setOnClickListener { onProductClick(productInCart) }
        }

    }

    fun updateList(newProducts: List<ProductInCartModel>) {
        products = newProducts
        notifyDataSetChanged()
    }


}