package br.com.stant.mobile.challenge.resource.extension

import android.app.Activity
import android.view.View
import androidx.appcompat.widget.Toolbar
import br.com.stant.mobile.challenge.R

fun Activity.hideToolbar() {
    val toolbar = this.findViewById<Toolbar>(R.id.toolbar_main)
    toolbar.visibility = View.GONE
}

fun Activity.showToolbar() {
    val toolbar = this.findViewById<Toolbar>(R.id.toolbar_main)
    toolbar.visibility = View.VISIBLE
}