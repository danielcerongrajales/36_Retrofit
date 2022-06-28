package com.example.a36_retrofit.data.utils

data class StateMessage(val response: Response)

data class Response(
    val message: String?,
    val messageType: MessageType
)
sealed class MessageType{

    object Success : MessageType()

    object Error : MessageType()

    object Info : MessageType()

    object None : MessageType()
}