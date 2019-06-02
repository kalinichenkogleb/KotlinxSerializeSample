package com.moby.kotlinxserializationsample

import com.moby.kotlinxserializationsample.model.Car
import junit.framework.Assert.assertEquals
import kotlinx.serialization.json.Json
import org.junit.Test

class CarSerializationTest {

    private val DUMMY_CAR_JSON = "{markName:Tesla,modelName:ModelX,operationPeriod:1}"
    private val DUMMY_CAR = Car("Tesla", "ModelX", 1)

    @Test
    fun testCarSerialization() {
        assertEquals(DUMMY_CAR_JSON, Json.unquoted.stringify(Car.serializer(), DUMMY_CAR))
    }

    @Test
    fun testUserDeserialization() {
        assertEquals(DUMMY_CAR, Json.unquoted.parse(Car.serializer(), DUMMY_CAR_JSON))
    }
}