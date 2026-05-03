package com.example.fake_store.ui

import android.app.Application
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fake_store.R
import com.example.fake_store.dagger2.AppComponent
import com.example.fake_store.databinding.ActivityMainBinding
import javax.inject.Inject
import com.example.fake_store.dagger2.DaggerAppComponent

class FakeStore : Application() {
    val component: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }
}

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainActivityViewModelFactory
    private val viewModel: MainActivityViewModel by viewModels { viewModelFactory }
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as FakeStore).component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

    }

    override fun onStart() {
        super.onStart()
        setupNavigation()
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }


}