package com.aniketkadam.functionaldemo

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test

class FunctionalOperatorsImplementationKtTest {

    @Test
    fun `custom map works as expected`() {
        val intList = listOf(1, 2, 3, 4, 5)

        fun numberToString(number: Int): String {
            return number.toString()
        }

        val stringVersion: List<String> = customMap(intList, ::numberToString)

        assertThat(stringVersion, equalTo(listOf("1", "2", "3", "4", "5")))
    }

    @Test
    fun `custom extension function map works as expected`() {
        val intList = listOf(1, 2, 3, 4, 5)

        val stringVersion: List<String> =
            intList.extensionCustomMap { number -> number.toString() }

        assertThat(stringVersion, equalTo(listOf("1", "2", "3", "4", "5")))
    }

    @Test
    fun `custom extension function for each works as expected`() {
        val intList = listOf(1,2)

        customForEach(intList, { number -> println(number) })
    }

    @Test
    fun `custom for each works as expected`() {
        val intList = listOf(1,2)

        intList.extensionCustomForEach { number -> println(number) }
    }
}