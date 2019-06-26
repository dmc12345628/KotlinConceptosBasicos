package com.kotlin.course.conceptosbasicos

sealed class Filter {
    object None: Filter()
    class ByMediaType(val type: MediaItem.Type): Filter()
}