package com.io.domain.country.state

import android.content.Context
import com.io.domain.R


fun convertErrors(message: String, context: Context): String {
    return when (message) {
        context.getString(R.string.error_network) -> {
            context.getString(R.string.error_network_ru)
        }
        context.getString(R.string.error_http) -> {
            context.getString(R.string.error_http_ru)
        }
        context.getString(R.string.error_uncorrect) -> {
            context.getString(R.string.error_uncorrect_ru)
        }
        else -> context.getString(R.string.error_else)
    }

}