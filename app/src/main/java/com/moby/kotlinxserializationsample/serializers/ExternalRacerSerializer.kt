package com.moby.kotlinxserializationsample.serializers

import com.moby.externalmodule.ExternalRacer
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl

@Serializer(forClass = ExternalRacer::class)
object ExternalRacerSerializer : KSerializer<ExternalRacer> {
    override val descriptor: SerialDescriptor = object : SerialClassDescImpl("ExternalRacer") {
        init {
            addElement("firstName")
            addElement("lastName")
        }
    }

    override fun serialize(encoder: Encoder, obj: ExternalRacer) {
        val output = encoder.beginStructure(descriptor)
        output.encodeStringElement(descriptor, 0, obj.firstName)
        output.encodeStringElement(descriptor, 1, obj.lastName)
        output.endStructure(descriptor)
    }

    override fun deserialize(decoder: Decoder): ExternalRacer {
        val input = decoder.beginStructure(descriptor)
        var firstName = ""
        var lastName = ""

        loop@ while (true) {
            when (val i = input.decodeElementIndex(descriptor)) {
                CompositeDecoder.READ_DONE -> break@loop
                0 -> firstName = input.decodeStringElement(descriptor, i)
                1 -> lastName = input.decodeStringElement(descriptor, i)
                else -> throw SerializationException("Unknown index $i")
            }
        }
        input.endStructure(descriptor)
        return ExternalRacer(firstName, lastName)
    }
}