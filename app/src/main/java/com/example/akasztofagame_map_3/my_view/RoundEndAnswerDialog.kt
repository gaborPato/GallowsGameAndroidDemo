package com.example.akasztofagame_map_3.my_view

import android.content.Context
import android.support.annotation.Nullable
import android.support.v7.app.AlertDialog
import android.widget.ImageView
import com.example.akasztofagame_map_3.R


fun getRoundEndDialog(win: Boolean,  rightWord: MutableMap.MutableEntry<Any, Any>, context: Context): AlertDialog {


    val dialog: AlertDialog = createDialog(win, rightWord, context)
    val emojiIcon = if (win)
        R.drawable.happy_emoji
    else
        R.drawable.sad_emoji
    dialog.setIcon(emojiIcon)

    return dialog
}

private fun createDialog(win: Boolean,  rightWord: MutableMap.MutableEntry<Any, Any>, context: Context): AlertDialog {

    var imageView = ImageView(context)


    imageView.setImageResource(rightWord!!.value as Int)
    var title = "NYERTÉL"
    var message = "Gratula"
    if (!win) {
        imageView.setImageResource(R.drawable.gallows)

        title = "vesztettél"
        message = "Helyes válasz: ${rightWord.key}"
    }
    var dialog = AlertDialog.Builder(context)


            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("ok", { dialogInterface, which ->

                dialogInterface.dismiss()
            })
            .setView(imageView)

    return dialog.create()
}
