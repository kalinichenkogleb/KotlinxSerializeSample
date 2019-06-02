package com.moby.kotlinxserializationsample.serializers

import com.moby.kotlinxserializationsample.model.University
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl

@Serializer(forClass = University::class)
object UniversitySerializer : KSerializer<University> {

    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("University") {
        init {
            addElement("name")
            addElement("address")
        }
    }

    override fun serialize(encoder: Encoder, obj: University) {
        val output = encoder.beginStructure(descriptor)
        output.encodeStringElement(descriptor, 0, obj.name)
        output.encodeStringElement(descriptor, 1, obj.address)
        output.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): University {
        val input = decoder.beginStructure(descriptor)
        var name = ""
        var address = ""

        loop@ while (true) {
            when (val i = input.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> name = input.decodeStringElement(descriptor, i)
                1 -> address = input.decodeStringElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }

        input.endStructure(descriptor)
        return University(name, address)
    }

}