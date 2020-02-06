package com.aniketkadam.functionaldemo

fun <T, R> customMap(list: List<T>, operation: (T) -> R): List<R> {
    val resultList: MutableList<R> = mutableListOf()
    for (item in list) {
        resultList.add(operation(item))
    }
    return resultList
}

// Extension function version
fun <T, R> List<T>.extensionCustomMap(operation: (T) -> R): List<R> {
    val resultList: MutableList<R> = mutableListOf()
    for (item in this) {
        resultList.add(operation(item))
    }
    return resultList
}


fun <T> customForEach(list : List<T>, operation: (T) -> Unit) {
    for (item : T in list) {
        operation(item)
    }
}

fun <T> List<T>.extensionCustomForEach(operation: (T) -> Unit) {
    for (item in this) {
        operation(item)
    }
}