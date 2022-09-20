package com.compose.compose_demo.model

import android.app.Application
import android.util.Log
import androidx.annotation.Keep
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.httplibrary.net.HttpResult
import com.compose.httplibrary.net.RetrofitProvider
import com.facebook.stetho.common.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.GET

/**
 * author：JQZ
 * createTime：2022/9/20  22:51
 */

@Keep
data class TestFrameWork (
    val desc: String,
    val title: String,
    val url: String
)

interface MainApiService{
    @GET("banner/json")
    suspend fun testFrameworkDataScope(): HttpResult<List<TestFrameWork>>
}

class MainViewModel(application: Application):AndroidViewModel(application) {
    private val retrofit = RetrofitProvider.getInstance(application).getServiceKt("https://www.wanandroid.com/",MainApiService::class.java)

    fun getBannerData(){
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO){
                val testFrameworkDataScope = retrofit.testFrameworkDataScope()
                testFrameworkDataScope
            }

            Log.d("测试网络", "getBannerData: $data")
        }
    }
}