package com.example.finalproject.model

sealed class ServiceType{
    object Duck: ServiceType()
    object Affirmation:ServiceType()
    object Advice:ServiceType()
}
