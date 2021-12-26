package br.com.stant.mobile.challenge.resource.extension

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.stant.mobile.challenge.R
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar




fun Fragment.setupToolbarWithNavController(toolbar: MaterialToolbar) {
    val navController: NavController

    val navHostFragment = requireActivity().supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

    navController = navHostFragment.navController
    val appBarConfiguration = AppBarConfiguration(navController.graph)

    toolbar.setupWithNavController(navController, appBarConfiguration)
}

fun Fragment.showSnackBar(str: String) {
    Snackbar.make(requireView(), str, Snackbar.LENGTH_LONG).show()
}