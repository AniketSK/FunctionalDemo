package com.aniketkadam.functionaldemo

import android.accounts.NetworkErrorException

class SimulatingNetworkHandlingErrors {

    fun storeDataFromNetwork(
        requestParam: String,
        someApi: SomeApi,
        validation: Validation,
        secondApi: SecondApi,
        database: Database,
        view: View
    ) {
        executeFailableOperation<NetworkErrorException, List<NetworkItem>>(
            operation = { someApi.getItemsFromNetwork(requestParam) },
            onSuccess = { data -> onSuccess(data, validation, secondApi, database) },
            onError = { error -> onError(error, view) }
        )
    }

    private fun onError(
        error: NetworkErrorException,
        view: View
    ) {
        view.showError("Error was ${error.message}")
    }

    private fun onSuccess(
        data: List<NetworkItem>,
        validation: Validation,
        secondApi: SecondApi,
        database: Database
    ) {
        data.filter(validation::isValid)
            .map(secondApi::getSecondaryInfo)
            .map(database::insert)
    }

}
