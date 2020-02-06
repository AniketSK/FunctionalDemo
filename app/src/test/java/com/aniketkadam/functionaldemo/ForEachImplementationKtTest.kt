package com.aniketkadam.functionaldemo

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test

class ForEachImplementationKtTest{

    @Test
    fun `custom for each works as expected`() {
        val intList = listOf(1,2,3,4,5)

        val stringVersion : List<String> = customForEach(intList) { number -> number.toString() }

        assertThat(stringVersion, equalTo(listOf("1","2","3","4","5")))
    }

    @Test
    fun `custom extension function for each works as expected`() {
        val intList = listOf(1,2,3,4,5)

        val stringVersion : List<String> = intList.extensionCustomForEach { number -> number.toString() }

        assertThat(stringVersion, equalTo(listOf("1","2","3","4","5")))
    }
}