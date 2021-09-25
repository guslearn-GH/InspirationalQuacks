package com.example.finalproject.model.common

sealed class ServiceType{
    object Duck: ServiceType()
    object Affirmation: ServiceType()
    object Advice: ServiceType()
}
