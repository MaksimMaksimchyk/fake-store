package com.example.fake_store.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.fake_store.data.storage.AuthManager
import com.example.fake_store.databinding.FragmentProfileBinding
import com.example.fake_store.ui.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
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
                viewModel.changeToken(AuthManager.getToken(requireContext()) ?: "")
                viewModel.currentToken.collect { token ->
                    if (token == "") {
                        binding.authLayout.visibility = View.VISIBLE
                    } else {
                        binding.tokenTextView.visibility = View.VISIBLE
                        binding.tokenTextView.text = "Ваш токен: $token"
                        binding.authLayout.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setupListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.createToken(
                requireContext(),
                binding.editTextTextEmailAddress.text.toString(),
                binding.editTextTextPassword.text.toString()
            )
        }
    }
}