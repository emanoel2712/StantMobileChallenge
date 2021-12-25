package br.com.stant.mobile.challenge.resource.extension

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.stant.mobile.challenge.R
import com.google.android.material.appbar.MaterialToolbar

fun Activity.hideToolbar() {
    val toolbar = this.findViewById<Toolbar>(R.id.toolbar_main)
    toolbar.visibility = View.GONE
}

fun Activity.showToolbar() {
    val toolbar = this.findViewById<Toolbar>(R.id.toolbar_main)
    toolbar.visibility = View.VISIBLE
}