package com.example.a36_retrofit.data.utils

data class StateMessage(val response: Response?=Response())

data class Response(
    val message: String?="empty state",
    val messageType: MessageType?=MessageType.None
)
sealed class MessageType{

    object Success : MessageType()

    object Error : MessageType()

    object Info : MessageType()

    object None : MessageType()
}