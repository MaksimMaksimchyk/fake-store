package com.example.fake_store.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fake_store.databinding.FragmentCatalogBinding
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.ui.fragments.adapter.ProductsAdapter

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        //Тестовый лист
        val test = listOf<ProductModel>(
            ProductModel(
                category = "te",
                description = "comprehensam",
                id = 3496,
                image = "singulis",
                price = 2.3,
                title = "inimicus"
            ), ProductModel(
                category = "dicant",
                description = "senectus",
                id = 9525,
                image = "sonet",
                price = 6.7,
                title = "proin"
            ), ProductModel(
                category = "mei",
                description = "signiferumque",
                id = 8903,
                image = "reque",
                price = 10.11,
                title = "sed"
            )
        )
        adapter.updateList(test)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        adapter = ProductsAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


}