package com.safframework.ext

import java.io.Closeable

/**
 *
 * @FileName:
 * @version V1.0 <描述当前版本功能>
 */

fun Closeable?.closeQuietly() {
    try {
        this?.close()
    } catch (e: Throwable) {
        // ignore
    }
}

inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
    var closed = false
    try {
        return block(this)
    } catch (e: Exception) {
        closed = true
        try {
            this?.close()
        } catch (closeException: Exception) {
        }
        throw e
    } finally {
        if (!closed) {
            this?.close()
        }
    }
}