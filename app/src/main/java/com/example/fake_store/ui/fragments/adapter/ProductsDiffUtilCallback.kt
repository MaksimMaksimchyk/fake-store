package com.example.fake_store.ui.fragments.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.fake_store.domain.ProductModel

class ProductsDiffUtilCallback(
    private val oldList: List<ProductModel>,
    private val newList: List<ProductModel>
) :
    DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldProduct = oldList[oldItemPosition]
        val newProduct = newList[newItemPosition]

        return oldProduct.id == newProduct.id
    }
}