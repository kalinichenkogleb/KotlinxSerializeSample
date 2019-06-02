package com.moby.kotlinxserializationsample

import com.moby.kotlinxserializationsample.model.Student
import com.moby.kotlinxserializationsample.model.University
import junit.framework.Assert.assertEquals
import kotlinx.serialization.json.Json
import org.junit.Test

class StudentSerializerTest {

    private val DUMMY_UNIVERSITY = University("MIT", "USA")
    private val DUMMY_STUDENT = Student("Johnson", DUMMY_UNIVERSITY)
    private val DUMMY_STUDENT_JSON = "{name:Johnson,university:{name:MIT,address:USA}}"

    @Test
    fun testStudentSerialization() {
        assertEquals(DUMMY_STUDENT_JSON, Json.unquoted.stringify(Student.serializer(), DUMMY_STUDENT))
    }

    @Test
    fun testStudentDeserialization() {
        assertEquals(DUMMY_STUDENT, Json.unquoted.parse(Student.serializer(), DUMMY_STUDENT_JSON))
    }
}