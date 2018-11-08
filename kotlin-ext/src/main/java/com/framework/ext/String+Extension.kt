package com.framework.ext

fun String.toIntSafely(defaultValue: Int = 0): Int {
    return try {
        this.toInt()
    } catch (e: Exception) {
        defaultValue
    }
}
