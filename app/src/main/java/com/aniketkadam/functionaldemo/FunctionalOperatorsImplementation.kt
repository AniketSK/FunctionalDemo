package com.aniketkadam.functionaldemo

fun <I, O> customMap(list: List<I>, operation: (I) -> O): List<O> {
    val resultList: MutableList<O> = mutableListOf()
    for (item in list) {
        resultList.add(operation(item))
    }
    return resultList
}

// Extension function version
fun <I, O> List<I>.extensionCustomMap(operation: (I) -> O): List<O> {
    val resultList: MutableList<O> = mutableListOf()
    for (item in this) {
        resultList.add(operation(item))
    }
    return resultList
}


fun <I> customForEach(list : List<I>, operation: (I) -> Unit) {
    for (item  in list) {
        operation(item)
    }
}

fun <I> List<I>.extensionCustomForEach(operation: (I) -> Unit) {
    for (item in this) {
        operation(item)
    }
}