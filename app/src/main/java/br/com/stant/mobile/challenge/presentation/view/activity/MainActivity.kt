package br.com.stant.mobile.challenge.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import br.com.stant.mobile.challenge.R
import br.com.stant.mobile.challenge.databinding.ActivityMainBinding
import br.com.stant.mobile.challenge.resource.extension.setStatusBarTransparent

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)

        this.setStatusBarTransparent(binding.root)
    }
}