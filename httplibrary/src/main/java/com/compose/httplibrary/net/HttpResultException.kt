package com.compose.httplibrary.net

/**
 * author：JQZ
 * createTime：2022/9/20  22:17
 */
class HttpResultException(message: String,val code: Int):RuntimeException(message) {
    constructor(httpResult: HttpResult<*>): this(httpResult.errorMsg,httpResult.getCode())
}