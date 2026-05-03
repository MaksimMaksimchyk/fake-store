package com.example.fake_store.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fake_store.R
import com.example.fake_store.databinding.FragmentCatalogBinding
import com.example.fake_store.domain.ProductModel
import com.example.fake_store.ui.FakeStore
import com.example.fake_store.ui.MainActivityViewModel
import com.example.fake_store.ui.MainActivityViewModelFactory
import com.example.fake_store.ui.fragments.adapters.ProductsAdapter
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogFragment : Fragment() {
    private var _binding: FragmentCatalogBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ProductsAdapter

    @Inject
    lateinit var viewModelFactory: MainActivityViewModelFactory
    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        (requireActivity().application as FakeStore).component.inject(this)
        super.onAttach(context)
    }

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
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        adapter = ProductsAdapter(this::onProductClick, viewModel.currentUsdRate)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.allProducts.collect { productsList ->
                    if (productsList.isEmpty()) binding.progressBar.visibility =
                        View.VISIBLE else binding.progressBar.visibility = View.INVISIBLE
                    adapter.updateList(productsList)
                }
            }
        }
    }

    private fun onProductClick(product: ProductModel) {
        viewModel.changeCurrentDetailsProduct(product)
        findNavController().navigate(R.id.action_catalogFragment_to_productDetailsFragment)
    }


}