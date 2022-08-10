package com.example.a36_retrofit.data.utils


sealed class DataState<T>
    // (   var stateMessage: StateMessage? = null,
//    var data: T? = null,
//    var stateEvent: StateEvent? = null
//)
 {
//    class Loading<T>:  DataState<T>()

     data class Error <T> (val response: StateMessage, val  data : T? = null): DataState<T>()
//     data class Error <T> (val response: StateMessage, val  data : T? = null, val stateEvent: StateEvent?): DataState<T>()

     data class Data <T>(val response: StateMessage?, val data: T? = null): DataState<T>()
//     data class Data <T>(val response: StateMessage?, val data: T? = null, val stateEvent: StateEvent?): DataState<T>()
//
        }

//    companion object {
//        fun <T> loading(): DataState<T> {
//            return DataState()
//        }

//        fun <T> error(response: Response, stateEvent: StateEvent?): DataState<T> {
//            return DataState(
//                stateMessage = StateMessage(response),
//                data = null,
//                stateEvent = stateEvent
//            )
//        }

//        fun <T> data(response: Response?, data: T? = null, stateEvent: StateEvent?): DataState<T> {
//            return DataState(
//                stateMessage = response?.let {
//                    StateMessage(it)
//                },
//                data = data,
//                stateEvent = stateEvent
//            )
//        }
//    }
//}