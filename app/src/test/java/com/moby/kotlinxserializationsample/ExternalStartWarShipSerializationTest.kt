package com.moby.kotlinxserializationsample

import com.moby.externalmodule.ExternalRacer
import com.moby.kotlinxserializationsample.model.StartWarShip
import junit.framework.Assert.assertEquals
import kotlinx.serialization.json.Json
import org.junit.Test
import java.util.*

class ExternalStartWarShipSerializationTest {

    private val DUMMY_EXTERNAL_RACER_SERIALIZATION = ExternalRacer("Anakin", "Skywalker")
    private val DUMMY_START_WAR_SHIP = StartWarShip(Date(1538636400000L), DUMMY_EXTERNAL_RACER_SERIALIZATION)
    private val DUMMY_START_WAR_SHIP_JSON = "{date:\"04/10/2018 09:00:00.000\",racer:{firstName:Anakin,lastName:Skywalker}}"

    @Test
    fun testExternalUserSerialization() {
        assertEquals(DUMMY_START_WAR_SHIP_JSON, Json.unquoted.stringify(StartWarShip.serializer(), DUMMY_START_WAR_SHIP))
    }

    @Test
    fun testExternalUserDeserialization() {
        assertEquals(DUMMY_START_WAR_SHIP, Json.unquoted.parse(StartWarShip.serializer(), DUMMY_START_WAR_SHIP_JSON))
    }
}