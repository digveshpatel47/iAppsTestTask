package com.iapps.exts

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(context: Context?,imageUrl:String?){
    context?.let { Glide.with(it).load(imageUrl).into(this) }
}

