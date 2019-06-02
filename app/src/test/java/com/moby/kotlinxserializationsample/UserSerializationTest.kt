package com.moby.kotlinxserializationsample

import com.moby.kotlinxserializationsample.model.User
import junit.framework.Assert.assertEquals
import kotlinx.serialization.json.Json
import org.junit.Test

class UserSerializationTest {

    private val DUMMY_JEDI_USER_JSON = "{firstName:Anakin,lastName:Skywalker}"
    private val DUMMY_JEDI_USER = User("Anakin", "Skywalker")

    @Test
    fun testUserSerialization() {
        assertEquals(DUMMY_JEDI_USER_JSON, Json.unquoted.stringify(User.serializer(), DUMMY_JEDI_USER))
    }

    @Test
    fun testUserDeserialization() {
        assertEquals(DUMMY_JEDI_USER, Json.unquoted.parse(User.serializer(), DUMMY_JEDI_USER_JSON))
    }
}