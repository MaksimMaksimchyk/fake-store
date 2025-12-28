package com.example.fake_store.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.fake_store.databinding.FragmentProductDetailsBinding
import com.example.fake_store.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.getValue
import kotlin.random.Random
import kotlin.random.nextInt

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.currentDetailsProduct.collect { currentProduct ->
                    Glide.with(binding.productImage).load(currentProduct.image)
                        .into(binding.productImage)
                    binding.productTitle.text = currentProduct.title
                    binding.productPrice.text = "$" + currentProduct.price.toString()
                    binding.productDescription.text = currentProduct.description
                }
            }
        }
    }

    private fun setupListeners() {
        binding.addToCartButton.setOnClickListener {
            viewModel.addToCart(viewModel.currentDetailsProduct.value)
            Toast.makeText(requireContext(), "Успешно добавлено в корзину!", Toast.LENGTH_SHORT)
                .show()
        }
        binding.randomizePrice.setOnClickListener {
            val newPrice = Random.nextInt(1, 1000) / 1.0
            binding.productPrice.text = "$" + newPrice.toString()
            viewModel.changeProductPrice(newPrice)
            Toast.makeText(requireContext(), "Цена изменена", Toast.LENGTH_SHORT)
                .show()
        }
    }

}