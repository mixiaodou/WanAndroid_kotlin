package com.brucej.wanandroid_kotlin.core.net

import android.util.Log
import com.brucej.wanandroid_kotlin.core.beans.BaseResponse
import kotlinx.coroutines.*
import java.lang.Exception


object CoroutinueDataHandler {
    fun <T> handler(de: Deferred<BaseResponse<T>>, callback: DataCallback<T>) {
        GlobalScope.launch(Dispatchers.IO) {
            de?.let {
                try {
                    it.await().run {
                        //协程切换到 主线程执行
                        withContext(Dispatchers.Main) {
                            if (errorCode == 0) {
                                callback.onSuccess(data as T)
                            } else {
                                callback.onError(errorMsg)
                            }
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        callback.onFail()
                    }
                }

            }
        }
    }
}
