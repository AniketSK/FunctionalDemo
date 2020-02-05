package com.aniketkadam.functionaldemo

sealed class EitherDataOrError<A : Exception, B> {
    data class Error<A : Exception, B>(val left: A) : EitherDataOrError<A, B>()
    data class Data<A : Exception, B>(val right: B) : EitherDataOrError<A, B>()
}

fun <A : Exception, B> executeFailableOperation(
    operation: () -> EitherDataOrError<A, B>,
    onSuccess: (B) -> Unit,
    onError: (A) -> Unit
) =
    when (val result = operation()) {
        is EitherDataOrError.Error -> onError(result.left)
        is EitherDataOrError.Data -> onSuccess(result.right)
    }
