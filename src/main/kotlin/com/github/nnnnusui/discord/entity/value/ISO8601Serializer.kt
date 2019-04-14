package com.github.nnnnusui.discord.entity.value

import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.withName
import java.text.SimpleDateFormat
import java.util.Date

@Serializer(forClass = Date::class)
object ISO8601Serializer: KSerializer<Date>{
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX")

    override val descriptor = StringDescriptor.withName("Date <-> ISO9601Timestamp: String")
    override fun serialize(encoder: Encoder, obj: Date) {
        encoder.encodeString(dateFormat.format(obj))
    }
    override fun deserialize(decoder: Decoder): Date
        = dateFormat.parse(decoder.decodeString())
}