package com.example.cocktailtaskapp.utils

sealed class UiStatus<out T> {
     data object LOADING: UiStatus<Nothing>()
     data class SUCCESS<T>(val data:T) : UiStatus<T>()
     data class ERROR(val message: String) : UiStatus<Nothing>()
}