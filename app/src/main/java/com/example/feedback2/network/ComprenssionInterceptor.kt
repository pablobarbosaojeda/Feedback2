package com.example.feedback2.network

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import java.util.zip.GZIPInputStream

class CompressionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val compressedRequest = originalRequest.newBuilder()
            .header("Accept-Encoding", "gzip")
            .build()

        val response = chain.proceed(compressedRequest)
        // Verificar y descomprimir si es necesario
        return if (response.header("Content-Encoding") == "gzip") {
            val decompressedBody = GZIPInputStream(response.body?.byteStream()).bufferedReader().use {
                it.readText()
            }
            response.newBuilder()
                .body(ResponseBody.create(response.body?.contentType(), decompressedBody))
                .build()
        } else {
            response
        }
    }
}
