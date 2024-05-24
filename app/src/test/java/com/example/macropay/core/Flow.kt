package com.example.macropay.core

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

suspend fun <T> Flow<T>.collectAndCancel(delay: Long = DELAY_FLOW) {
    coroutineScope {
        val job = launch {
            this@collectAndCancel.collect()
        }
        delay(delay)
        job.cancel()
    }
}

private const val DELAY_FLOW = 250L