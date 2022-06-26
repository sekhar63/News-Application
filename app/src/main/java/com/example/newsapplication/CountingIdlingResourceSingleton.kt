package com.example.newsapplication

import androidx.test.espresso.idling.CountingIdlingResource

object CountingIdlingResourceSingleton {

    private const val RESOURCE = "GLOBAL"
    private const val RETRY_RESOURCE = "RETRY"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    @JvmField
    val countingIdlingRetryResource = CountingIdlingResource(RETRY_RESOURCE)

    fun incrementRetry() {
        countingIdlingRetryResource.increment()
    }

    fun decrementRetry() {
        countingIdlingRetryResource.decrement()
    }

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}