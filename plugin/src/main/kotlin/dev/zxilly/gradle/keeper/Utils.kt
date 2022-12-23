package dev.zxilly.gradle.keeper

import okhttp3.OkHttpClient
import okhttp3.brotli.BrotliInterceptor

object Utils {
    private val client = OkHttpClient.Builder()
        .addInterceptor(BrotliInterceptor)
        .build()

    fun get(url: String, headers: Map<String, String> = emptyMap()): String {
        val request = okhttp3.Request.Builder()
            .url(url)
            .apply {
                headers.forEach { (k, v) ->
                    header(k, v)
                }
            }
            .build()
        return client.newCall(request).execute().use {
            if (!it.isSuccessful) throw Exception("Unexpected code $it")
            it.body!!.string()
        }
    }
}