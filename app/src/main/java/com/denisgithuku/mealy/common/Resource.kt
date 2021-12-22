package com.denisgithuku.mealy.common

//data class Resource<out T>(val status: Status, val data: T?, val error: String?) {
//    companion object {
//        fun <T> loading(data: T? = null): Resource<T> {
//            return Resource(Status.LOADING, data, null)
//        }
//        fun <T> success(data: T?): Resource<T> {
//            return Resource(Status.SUCCESS, data, null)
//        }
//        fun <T> error(data: T? = null, error: String?): Resource<T> {
//            return Resource(Status.ERROR, data, error)
//        }
//    }
//}
//
//enum class Status {
//    LOADING,
//    SUCCESS,
//    ERROR
//}


sealed class Resource<T>(val data: T? = null, val error: String? = null) {
    class Loading<T>(data: T? = null) : Resource<T>(data, null)
    class Success<T>(data: T?): Resource<T>(data,null)
    class Error<T>(error: String?): Resource<T>(null, error)
}
