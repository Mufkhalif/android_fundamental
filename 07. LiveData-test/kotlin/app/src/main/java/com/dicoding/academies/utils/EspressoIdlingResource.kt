package com.dicoding.academies.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResorce = CountingIdlingResource(RESOURCE)

    fun increment()  {
        idlingResorce.increment()
    }

    fun decrement() {
        idlingResorce.decrement()
    }

}