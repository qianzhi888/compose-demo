package com.compose.httplibrary.net

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.annotations.TestOnly
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * author：JQZ
 * createTime：2022/9/20  22:31
 * Retrofit提供者
 */
class RetrofitProvider private constructor() {
    private val mOkHttpClient: OkHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        OkHttpClient.Builder()
            .also { configOkhttp(it) }
            .build()
    }

    private fun configOkhttp(builder: OkHttpClient.Builder) {
        builder.callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            //.cookieJar()
            .addNetworkInterceptor(StethoInterceptor())
            //日志拦截器
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
    }

    companion object{
        @Volatile
        private var retrofitProvider: RetrofitProvider? = null

        @JvmStatic
        @TestOnly
        fun setInstance(okHttpClient: OkHttpClient){
            retrofitProvider = RetrofitProvider()
        }

        fun getInstance(context: Context): RetrofitProvider{
            if(retrofitProvider == null){
                synchronized(RetrofitProvider::class){
                    if(retrofitProvider == null){
                        retrofitProvider = RetrofitProvider()
                    }
                }
            }
            return retrofitProvider!!
        }

    }

    private data class ApiServiceEntry(
        val apiService: Any,
        val baseUrl: String,
        val apiServiceClass: Class<*>
    )

    /**apiService缓存*/
    private val serviceCacheKt = ArrayList<ApiServiceEntry>()

    /**
     * 支持kotlin的空安全和默认值
     */
    @Synchronized
    fun <T> getServiceKt(baseUrl: String,serviceClass: Class<T>): T{
        for (apiServiceEntry in serviceCacheKt) {
            if(apiServiceEntry.apiServiceClass == serviceClass && apiServiceEntry.baseUrl == baseUrl){
                return apiServiceEntry.apiService as T
            }
        }

        return Retrofit.Builder()
            .callFactory(mOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(serviceClass)
            .also { serviceCacheKt.add(ApiServiceEntry(it as Any,baseUrl,serviceClass)) }
    }
}