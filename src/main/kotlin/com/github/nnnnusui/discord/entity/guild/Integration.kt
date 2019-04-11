package com.github.nnnnusui.discord.entity.guild

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

// @formatter:off
data class Integration(
                                            val id:                Snowflake
 ,                                          val name:              String
 ,                                          val type:              String    // (twitch, youtube, etc)
 ,@SerialName("enabled"            ) val isEnabled:         Boolean
 ,@SerialName("syncing"            ) val isSyncing:         Boolean
 ,@SerialName("role_id"            ) val roleId:            Snowflake // "subscribers"
 ,@SerialName("expire_behavior"    ) val expireBehavior:    Int
 ,@SerialName("expire_grace_period") val expireGracePeriod: Int
 ,                                          val user:              User
 ,                                          val account:           Account
 ,@Serializable(with = ISO8601Serializer::class)
  @SerialName("synced_at"          ) val syncedAt:          Date
){
    @Serializable
    data class Account(
      val id:   String
     ,val name: String
    )
}