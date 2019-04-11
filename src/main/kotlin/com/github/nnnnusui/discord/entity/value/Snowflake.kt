package com.github.nnnnusui.discord.entity.value

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.withName

// @formatter:off
@Serializable
data class Snowflake(val raw: String){
    @Serializer(forClass = Snowflake::class)
    companion object: KSerializer<Snowflake> {
        override val descriptor: SerialDescriptor = StringDescriptor.withName("snowflake: String")
        override fun serialize(encoder: Encoder, obj: Snowflake)
        { encoder.encodeString(obj.raw) }
        override fun deserialize(decoder: Decoder): Snowflake
                = Snowflake(decoder.decodeString())
    }

    val timestampInMS:     Long by lazy { raw.toULong().shr(22).toLong() + 1420070400000 }
    val internalWorkerID:  Int  by lazy { raw.toULong().and(0x3E0000.toULong()).shr(17).toInt() }
    val internalProcessID: Int  by lazy { raw.toULong().and(0x1F000 .toULong()).shr(12).toInt() }
    val increment:         Int  by lazy { raw.toULong().and(0xFFF   .toULong()).toInt() }
    override fun toString() = raw
}