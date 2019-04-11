package com.github.nnnnusui.discord.entity.user

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialDescriptor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.withName
import java.lang.IllegalArgumentException

// @formatter:off
@Serializable
data class User(
                                               val id:            Snowflake
 ,                                             val username:      String?          =null  // not unique
 ,                                             val discriminator: String?          =null  // TODO 4-digit discord-tag
 ,                                             val avatar:        String?          = null // nullable // TODO avatar hash
 ,@SerialName("bot"         ) @Optional val isBot:         Boolean?         =null
 ,@SerialName("mfa_enabled" ) @Optional val isMfaEnabled:  Boolean?         =null
 ,                                   @Optional val locale:        String?          =null
 ,@SerialName("verified"    ) @Optional val isVerified:    Boolean?         =null
 ,                                   @Optional val email:         String?          =null
 ,                                   @Optional val flags:         User.Flag?       =null
 ,@SerialName("premium_type") @Optional val premiumType:   User.PremiumType?=null
){
    enum class Flag(val code: Int){
        NONE(0)
       ,DISCORD_EMPLOYEE(1)
       ,DISCORD_PARTNER( 2)
       ,HYPESQUAD_EVENTS(4)
       ,BUG_HUNTER(      8)
       ,HOUSE_BRAVERY(  16)
       ,HOUSE_BALANCE(  32)
       ,EARLY_SUPPORTER(64)
        ;
        @Serializer(forClass = Flag::class)
        companion object: KSerializer<Flag> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("DefaultMessageNotificationLevel <-> code: Int")
            override fun serialize(encoder: Encoder, obj: Flag)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): Flag {
                val num = decoder.decodeInt()
                return Flag.values().firstOrNull { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
    enum class PremiumType(val code: Int){
        NITRO_CLASSIC(1)
       ,NITRO(        2)
        ;
        @Serializer(forClass = PremiumType::class)
        companion object: KSerializer<PremiumType> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("DefaultMessageNotificationLevel <-> code: Int")
            override fun serialize(encoder: Encoder, obj: PremiumType)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): PremiumType {
                val num = decoder.decodeInt()
                return PremiumType.values().firstOrNull { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
}