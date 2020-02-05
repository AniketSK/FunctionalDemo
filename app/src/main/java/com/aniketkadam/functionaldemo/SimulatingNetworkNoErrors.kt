package com.aniketkadam.functionaldemo

import android.accounts.NetworkErrorException

/**
 * Note:
 * This simulates the following:
 * 1. Get data from api1
 * 2. Filter items that don't match some criteria
 * 3. For the valid items, make a second api call
 * 4. Store the result of the second api call into the database
 *
 * Limitations of this implementation:
 * 1. There's no background processing
 * 2. If this was a true reactive stream, the second api would be a switchMap, instead of a map
 */
class SimulatingNetworkNoErrors {
    fun storeDataFromNetwork(requestParam : String,
                           someApi: SomeApi,
                           validation: Validation,
                           secondApi: SecondApi,
                           database: Database) =

        someApi.getItemsFromNetwork(requestParam)
            .filter(validation::isValid)
            .map(secondApi::getSecondaryInfo)
            .map(database::insert)
}

interface SomeApi {
    @Throws(NetworkErrorException::class)
    fun getItemsFromNetwork(param : String) : List<NetworkItem>        }

interface Validation {
    fun isValid(item : NetworkItem) : Boolean        }

interface SecondApi {
    fun getSecondaryInfo(item : NetworkItem) : SecondNetItem    }

interface Database {
    fun insert(secondNetItem : SecondNetItem)   }

interface View {
    fun showError(message : String)
    fun showData(item : SecondNetItem) }

data class NetworkItem(val data : String)
data class SecondNetItem(val data : String)


