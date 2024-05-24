package com.example.macropay.core

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat

inline fun <reified T> classType(): Class<T> = T::class.java

inline fun <reified T> assertThatIsInstanceOf(actual: Any?) = assertThat(
    actual, instanceOf(
        classType<T>()
    )
)

fun assertIsNull(actual: Any?) = assertThat(actual, nullValue())

fun assertThatEquals(actual: Any?, expected: Any?) = assertThat(actual, equalTo(expected))
