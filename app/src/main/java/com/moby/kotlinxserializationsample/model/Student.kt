package com.moby.kotlinxserializationsample.model

import com.moby.kotlinxserializationsample.serializers.UniversitySerializer
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val name: String,
    @Serializable(with = UniversitySerializer::class) val university: University)

data class University(val name: String, val address: String)