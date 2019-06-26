package com.kotlin.course.conceptosbasicos

data class MediaItem(val id: Int, val title: String, val thumbUrl: String, val type: Type) {
    enum class Type { PHOTO, VIDEO }
}

