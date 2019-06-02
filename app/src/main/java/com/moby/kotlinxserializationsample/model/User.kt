package com.moby.kotlinxserializationsample.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName: String,
    val lastName: String,
    @SerialName("full_address") val address: String
)