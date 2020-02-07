package com.aniketkadam.functionaldemo

/**
 * A type that can encapsulate the result, no matter what it is
 */
sealed class EitherDataOrError<E : Exception, Data> {
    data class Error<E : Exception, Data>(val left: E) : EitherDataOrError<E, Data>()
    data class Data<E : Exception, Data>(val right: Data) : EitherDataOrError<E, Data>()
}

/**
 * Abstract the process of
 * 1. Running a failable operation
 * 2. If it succeeds, run the success case
 * 3. If it fails, run the failure case
 */
inline fun <reified E : Exception, Data> executeFailableOperation(
    operation: () -> Data,
    onSuccess: (Data) -> Unit,
    onError: (E) -> Unit
) =
    when (val result = convertToEither<E, Data> { operation() }) {
        is EitherDataOrError.Error -> onError(result.left)
        is EitherDataOrError.Data -> onSuccess(result.right)
    }

/**
 * Converts a regular operation, which may fail, into the type operation that generates an Either
 */
inline fun <reified E : Exception, Data> convertToEither(operation: () -> Data): EitherDataOrError<E, Data> {
    return try {
        EitherDataOrError.Data(operation())
    } catch (error: Exception) {
        if (error is E)
            EitherDataOrError.Error(error)
        else
            throw error
    }
}
