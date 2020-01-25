package com.kirdmt.simpleplayer.repository

import com.kirdmt.simpleplayer.data.TrackSearchResults
import com.kirdmt.simpleplayer.utils.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.SEARCH_URL_COMPONENT)
    fun search(@Query(Constants.SEARCH_URL_TERM) query: String): Observable<TrackSearchResults>

    /**
     * Companion object to create the ApiService
     */

    companion object Factory {

        val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client : OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}