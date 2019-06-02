package com.moby.kotlinxserializationsample.model

import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl

@Serializable
data class Car(val markName: String, val modelName: String, val operationPeriod: Int) {

    @Serializer(forClass = Car::class)
    companion object : KSerializer<Car> {

        override val descriptor: SerialDescriptor = object : SerialClassDescImpl("Car") {
            init {
                addElement("markName")
                addElement("modelName")
                addElement("operationPeriod")
            }
        }

        override fun serialize(encoder: Encoder, obj: Car) {
            val compositeOutput = encoder.beginStructure(descriptor)
            compositeOutput.encodeStringElement(descriptor, 0, obj.markName)
            compositeOutput.encodeStringElement(descriptor, 1, obj.modelName)
            compositeOutput.encodeIntElement(descriptor, 2, obj.operationPeriod)
            compositeOutput.endStructure(descriptor)
        }

        override fun deserialize(decoder: Decoder): Car {
            val compositeInput = decoder.beginStructure(descriptor)
            var markName = ""
            var modelName = ""
            var operationPeriod = 0

            loop@ while (true) {
                when (val i = compositeInput.decodeElementIndex(descriptor)) {
                    CompositeDecoder.READ_DONE -> break@loop
                    0 -> markName = compositeInput.decodeStringElement(descriptor, i)
                    1 -> modelName = compositeInput.decodeStringElement(descriptor, i)
                    2 -> operationPeriod = compositeInput.decodeIntElement(descriptor, i)
                    else -> throw SerializationException("Unknown index $i")
                }
            }

            compositeInput.endStructure(descriptor)
            return Car(markName, modelName, operationPeriod)
        }
    }
}