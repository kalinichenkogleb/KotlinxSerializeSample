package com.moby.kotlinxserializationsample.model

import com.moby.externalmodule.ExternalRacer
import com.moby.kotlinxserializationsample.serializers.DateSerializer
import com.moby.kotlinxserializationsample.serializers.ExternalRacerSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class StartWarShip(
    @Serializable(with = DateSerializer::class) val date: Date,
    @Serializable(with = ExternalRacerSerializer::class) val racer: ExternalRacer
)