package br.com.stant.mobile.challenge.resource.extension

import android.view.View
import android.view.animation.AnimationUtils
import br.com.stant.mobile.challenge.R

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.animationPushToUp() {
    this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_up_in))
}

fun View.animationPushRight() {
    this.startAnimation(AnimationUtils.loadAnimation(context, R.anim.push_right_in))
}
