package io.appeiron.clientapp.data

import io.appeiron.clientapp.data.Status.*

/**
 * Generic class for server response with a loading status
 */

data class ServerResponse<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ServerResponse<T> {
            return ServerResponse(SUCCESS, data, null)
        }

        fun <T> error(msg: String?, data: T?): ServerResponse<T> {
            return ServerResponse(ERROR, data, msg)
        }

        fun <T> loading(data: T?): ServerResponse<T> {
            return ServerResponse(LOADING, data, null)
        }
    }
}
