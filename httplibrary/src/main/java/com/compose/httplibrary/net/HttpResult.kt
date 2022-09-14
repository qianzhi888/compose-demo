package com.compose.httplibrary.net

import androidx.annotation.Keep

/**
 * author：JQZ
 * createTime：2022/9/14  23:06
 */
@Keep
data class HttpResult<T>(
    val data: T?,
    val errorMsg: String,
    val errorCode: Int
){
    /**
     * 是否请求成功
     */
    fun isSuccess(): Boolean = errorCode == SUCCESS_CODE
    companion object{
        const val SUCCESS_CODE = 0
    }
}