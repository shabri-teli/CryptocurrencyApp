package com.example.cryptocurrencyapp.Network

import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class NetworkClient {
    companion object {
        fun getUnsafeOkHttpClient(): OkHttpClient {
            try {
                // Create a trust manager that accepts all certificates
                val trustManager = object : X509TrustManager {
                    override fun checkClientTrusted(
                        chain: Array<out X509Certificate>?,
                        authType: String?
                    ) {
                    }

                    override fun checkServerTrusted(
                        chain: Array<out X509Certificate>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
                }

                // Install the all-trusting trust manager
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, arrayOf<TrustManager>(trustManager), SecureRandom())

                // Create an OkHttpClient that trusts all certificates
                return OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.socketFactory, trustManager)
                    .hostnameVerifier { _, _ -> true }
                    .build()

            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}
