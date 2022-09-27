package com.example.profnotes.core

import android.view.View
import com.example.profnotes.R

fun View.gone() {
    visibility = View.GONE

}

fun View.visible() {
    visibility = View.VISIBLE

}

fun View.invisible() {
    visibility = View.INVISIBLE

}

fun View.passive() {
    setBackgroundResource(R.drawable.bg_button_passive)
}


fun View.active() {
    setBackgroundResource(R.drawable.bg_button_active)
}