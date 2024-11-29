package com.dicoding.mybottomnavtest.preference

sealed class ResultValue<out R> private constructor()  {
    data class Success<out T>(val data: T) : ResultValue<T>()
    data class Error(val error: String) : ResultValue<Nothing>()
    object Loading : ResultValue<Nothing>()
}