package com.github.nnnnusui.discord.entity.guild

import com.github.nnnnusui.discord.entity.Emoji
import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.voice.VoiceState
import com.github.nnnnusui.discord.entity.channel.Channel
import com.github.nnnnusui.discord.entity.permission.Role
import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import com.github.nnnnusui.discord.entity.value.Snowflake
import com.github.nnnnusui.discord.event.presence.PresenceUpdate
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
import java.util.Date

// @formatter:off
@Serializable
data class Guild(
  @SerialName("id"                           )           val id:                          Snowflake
  /* Not listed in the docs.                       */
 ,@SerialName("lazy"                         ) @Optional val lazy:                        Boolean?   =null
// ,@SerialName("guild_id"                     ) @Optional val guildId:                     Snowflake? =null
// ,@SerialName("premium_tier"                 ) @Optional val premiumTier:                 Int?       =null
  /* --------------------------------------------- */
 ,@SerialName("name"                         )           val name:                        String                 // 2-100 characters
 ,@SerialName("icon"                         )           val icon:                        String?                // TODO icon hash
 ,@SerialName("splash"                       )           val splash:                      String?                // TODO splash hash
 ,@SerialName("owner"                        ) @Optional val isOwner:                     Boolean?               =null
 ,@SerialName("owner_id"                     )           val ownerId:                     Snowflake
 ,@SerialName("permissions"                  ) @Optional val permissions:                 Int?                   =null
 ,@SerialName("region"                       )           val region:                      String                 // voice region id
 ,@SerialName("afk_channel_id"               )           val afkChannelId:                Snowflake?
 ,@SerialName("afk_timeout"                  )           val afkTimeout:                  Int
 ,@SerialName("embed_enabled"                ) @Optional val isEmbedEnabled:              Boolean?               =null
 ,@SerialName("embed_channel_id"             ) @Optional val embedChannelId:              Snowflake?             =null
 ,@SerialName("verification_level"           )           val verificationLevel:           Guild.VerificationLevel
 ,@SerialName("default_message_notifications")           val defaultMessageNotifications: Guild.DefaultMessageNotificationLevel
 ,@SerialName("explicit_content_filter"      )           val explicitContentFilter:       Guild.ExplicitContentFilterLevel
 ,@SerialName("roles"                        )           val roles:                       Array<Role  >
 ,@SerialName("emojis"                       )           val emojis:                      Array<Emoji >
 ,@SerialName("features"                     )           val features:                    Array<String>
 ,@SerialName("mfa_level"                    )           val mfaLevel:                    Guild.MFALevel
 ,@SerialName("application_id"               )           val applicationId:               Snowflake?
 ,@SerialName("widget_enabled"               ) @Optional val isWidgetEnabled:             Boolean?               =null
 ,@SerialName("widget_channel_id"            ) @Optional val widgetChannelId:             Snowflake?             =null
 ,@SerialName("system_channel_id"            )           val systemChannelId:             Snowflake?
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName("joined_at"                    ) @Optional val joinedAt:                    Date?                  =null
 ,@SerialName("large"                        ) @Optional val isLarge:                     Boolean?               =null
 ,@SerialName("unavailable"                  ) @Optional val isUnavailable:               Boolean?               =null
 ,@SerialName("member_count"                 ) @Optional val memberCount:                 Int?                   =null
 ,@SerialName("voice_states"                 ) @Optional val voiceStates:                 Array<VoiceState    >? =null
 ,@SerialName("members"                      ) @Optional val members:                     Array<Guild.Member  >? =null
 ,@SerialName("channels"                     ) @Optional val channels:                    Array<Channel       >? =null
 ,@SerialName("presences"                    ) @Optional val presences:                   Array<PresenceUpdate>? =null // TODO partial
 ,@SerialName("max_presences"                )@Optional  val maxPresences:                Int?                   =null // diff to docs
 ,@SerialName("max_members"                  )@Optional  val maxMembers:                  Int?                   =null
 ,@SerialName("vanity_url_code"              )           val vanityUrlCode:               String?
 ,@SerialName("description"                  )           val description:                 String?
 ,@SerialName("banner"                       )           val banner:                      String?                // banner hash
){
    @Serializable(with = DefaultMessageNotificationLevel.Companion::class)
    enum class DefaultMessageNotificationLevel(val code: Int){
        ALL_MESSAGED(  0)
       ,ONLY_MENITIONS(1)
        ;
        @Serializer(forClass = DefaultMessageNotificationLevel::class)
        companion object: KSerializer<DefaultMessageNotificationLevel> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("${this::class}.code <-> code: Int")
            override fun serialize(encoder: Encoder, obj: DefaultMessageNotificationLevel)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): DefaultMessageNotificationLevel {
                val num = decoder.decodeInt()
                return DefaultMessageNotificationLevel.values().find { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
    @Serializable(with = ExplicitContentFilterLevel.Companion::class)
    enum class ExplicitContentFilterLevel(val code: Int){
        DISABLED(             0)
       ,MEMBERS_WITHOUT_ROLES(1)
       ,ALL_MEMBERS(          2)
        ;
        @Serializer(forClass = ExplicitContentFilterLevel::class)
        companion object: KSerializer<ExplicitContentFilterLevel> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("${this::class}.code <-> code: Int")
            override fun serialize(encoder: Encoder, obj: ExplicitContentFilterLevel)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): ExplicitContentFilterLevel {
                val num = decoder.decodeInt()
                return ExplicitContentFilterLevel.values().find { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
    @Serializable(with = MFALevel.Companion::class)
    enum class MFALevel(val code: Int){
        NONE(    0)
       ,ELEVATED(1)
        ;
        @Serializer(forClass = MFALevel::class)
        companion object: KSerializer<MFALevel> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("${this::class}.code <-> code: Int")
            override fun serialize(encoder: Encoder, obj: MFALevel)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): MFALevel {
                val num = decoder.decodeInt()
                return MFALevel.values().find { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
    @Serializable(with = VerificationLevel.Companion::class)
    enum class VerificationLevel(val code: Int){
        NONE(     0)
       ,LOW(      1)
       ,MIDIUM(   2)
       ,HIGH(     3)
       ,VERY_HIGH(4)
        ;
        @Serializer(forClass = VerificationLevel::class)
        companion object: KSerializer<VerificationLevel> {
            override val descriptor: SerialDescriptor = StringDescriptor.withName("${this::class}.code <-> code: Int")
            override fun serialize(encoder: Encoder, obj: VerificationLevel)
                    { encoder.encodeInt(obj.code) }
            override fun deserialize(decoder: Decoder): VerificationLevel {
                val num = decoder.decodeInt()
                return VerificationLevel.values().find { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
            }
        }
    }
    @Serializable
    data class Embed(
      @SerialName("enabled"   ) val isEnabled: Boolean
     ,@SerialName("channel_id") val channelId: Snowflake?
    )
    @Serializable
    data class Member(
      @SerialName("user"     )           val user:     User
     ,@SerialName("nick"     ) @Optional val nickname: String?          =null
     ,@SerialName("roles"    )           val roles:    Array<Snowflake> // Role object ids
     ,@Serializable(with = ISO8601Serializer::class)
      @SerialName("joined_at")           val joinedAt: Date
     ,@SerialName("deaf"     )           val isDeaf:   Boolean
     ,@SerialName("mute"     )           val isMute:   Boolean
    )
}