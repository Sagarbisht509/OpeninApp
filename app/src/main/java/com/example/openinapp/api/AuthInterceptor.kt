package com.example.openinapp.api

import com.example.openinapp.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject
    lateinit var tokenManager: TokenManager
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenManager.getToken()

        val request = chain.request().newBuilder()
        request.addHeader("Authorization", "Bearer $token")

        return chain.proceed(request.build())
    }
}