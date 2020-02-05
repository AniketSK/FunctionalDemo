package com.aniketkadam.functionaldemo

import android.accounts.NetworkErrorException

class SimulatingNetworkHandlingErrors {

    fun storeDataFromNetwork(
        requestParam: String,
        someApi: SomeApiMaybeErrors,
        validation: Validation,
        secondApi: SecondApi,
        database: Database,
        view: View
    ) {
        executeFailableOperation(
            operation = { someApi.getItemsFromNetwork(requestParam) },
            onSuccess = { data -> onSuccess(data, validation, secondApi, database) },
            onError = { error -> onError(error, view) }
        )
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

    private fun onError(error : NetworkErrorException,
                        view : View) {
        view.showError("Error was ${error.message}")
    }

    private fun onSuccess(data: List<NetworkItem>,
                          validation: Validation,
                          secondApi: SecondApi,
                          database: Database) {
        data.filter(validation::isValid)
            .map(secondApi::getSecondaryInfo)
            .map(database::insert)
    }

}

interface SomeApiMaybeErrors {
    fun getItemsFromNetwork(param: String): EitherDataOrError<NetworkErrorException, List<NetworkItem>>
}


sealed class EitherDataOrError<A : Exception, B> {
    data class Error<A : Exception, B>(val left: A) : EitherDataOrError<A, B>()
    data class Data<A : Exception, B>(val right: B) : EitherDataOrError<A, B>()
}

