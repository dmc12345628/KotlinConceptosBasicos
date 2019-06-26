package com.kotlin.course.conceptosbasicos

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.squareup.picasso.Picasso

fun Context.toast(message: String, duration: Int = LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

fun ProgressBar.show() { this.visibility = VISIBLE }
fun ProgressBar.hide() { this.visibility = INVISIBLE }

fun RecyclerView.ViewHolder.toast(message: String, duration: Int = LENGTH_SHORT) = itemView.context.toast(message, duration)

fun ViewGroup.inflate(layoutRes: Int): View = LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadUrl(thumbUrl: String) = Picasso.get().load(thumbUrl).into(this)

inline fun <reified T: View> View.find(idRes: Int): T = findViewById(idRes)

inline fun <reified T: View> RecyclerView.ViewHolder.find(idRes: Int): T = itemView.find(idRes)