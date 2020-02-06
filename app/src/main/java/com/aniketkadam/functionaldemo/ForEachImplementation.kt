package com.aniketkadam.functionaldemo

fun <T, R> customForEach(list: List<T>, operation: (T) -> R): List<R> {
    val resultList: MutableList<R> = mutableListOf()
    for (item in list) {
        resultList.add(operation(item))
    }
    return resultList
}

// Extension function version
fun <T, R> List<T>.extensionCustomForEach(operation: (T) -> R): List<R> {
    val resultList: MutableList<R> = mutableListOf()
    for (item in this) {
        resultList.add(operation(item))
    }
    return resultList
}
