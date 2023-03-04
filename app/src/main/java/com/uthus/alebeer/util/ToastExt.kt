package com.uthus.alebeer.util

import android.content.Context
import android.widget.Toast

object ToastExt {
    fun showToast(context: Context, content: Int) {
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show()
    }
}