package com.github.nnnnusui.discord.entity.guild

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

// @formatter:off
@Serializable
data class Integration(
  @SerialName("id"                 ) val id:                Snowflake
 ,@SerialName("name"               ) val name:              String
 ,@SerialName("type"               ) val type:              String     // (twitch, youtube, etc)
 ,@SerialName("enabled"            ) val isEnabled:         Boolean
 ,@SerialName("syncing"            ) val isSyncing:         Boolean
 ,@SerialName("role_id"            ) val roleId:            Snowflake  // "subscribers"
 ,@SerialName("expire_behavior"    ) val expireBehavior:    Int
 ,@SerialName("expire_grace_period") val expireGracePeriod: Int
 ,@SerialName("user"               ) val user:              User
 ,@SerialName("account"            ) val account:           Account
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName("synced_at"          ) val syncedAt:          Date
){
    @Serializable
    data class Account(
      @SerialName("id"  ) val id:   String
     ,@SerialName("name") val name: String
    )
}