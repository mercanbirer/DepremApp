package com.example.depremapp.data

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val throwable: Throwable?,
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String?, data: T?, throwable: Throwable?): Resource<T> {
            return Resource(Status.ERROR, data, msg, throwable)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null, null)
        }
    }
}
