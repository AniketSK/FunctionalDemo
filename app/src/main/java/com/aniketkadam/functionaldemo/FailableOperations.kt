package com.aniketkadam.functionaldemo

/**
 * A type that can encapsulate the result, no matter what it is
 */
sealed class EitherDataOrError<A : Exception , B> {
    data class Error<A : Exception, B>(val left: A) : EitherDataOrError<A, B>()
    data class Data<A : Exception, B>(val right: B) : EitherDataOrError<A, B>()
}

/**
 * Abstract the process of
 * 1. Running a failable operation
 * 2. If it succeeds, run the success case
 * 3. If it fails, run the failure case
 */
fun <A : Exception, B> executeFailableOperation(
    operation: () -> EitherDataOrError<A, B>,
    onSuccess: (B) -> Unit,
    onError: (A) -> Unit
) =
    when (val result = operation()) {
        is EitherDataOrError.Error -> onError(result.left)
        is EitherDataOrError.Data -> onSuccess(result.right)
    }


inline fun <reified A : Exception, B> convertToEither( operation: () -> B  ) : EitherDataOrError<A,B> {
    return try{
        EitherDataOrError.Data(operation())
    } catch (error : Exception) {
        if(error is A)
            EitherDataOrError.Error(error)
        else
            throw error
    }
}
