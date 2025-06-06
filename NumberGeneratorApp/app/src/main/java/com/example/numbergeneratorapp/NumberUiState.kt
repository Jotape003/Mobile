package com.example.numbergeneratorapp;

public open class NumberUiState {
    object Idle : NumberUiState()
    object Loading : NumberUiState()
    data class Success(val number : Int) : NumberUiState()
    data class Error(val message : String) : NumberUiState()
}
