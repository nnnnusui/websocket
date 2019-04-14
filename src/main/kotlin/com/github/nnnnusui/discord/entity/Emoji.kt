package com.github.nnnnusui.discord.entity

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Emoji(
  @SerialName("id"            )           val id:              Snowflake?
 ,@SerialName("name"          )           val name:            String
 ,@SerialName("roles"         ) @Optional val roles:           Array<Snowflake>? =null // Role.id
 ,@SerialName("user"          ) @Optional val creator:         User?             =null
 ,@SerialName("require_colons") @Optional val isRequireColons: Boolean?          =null
 ,@SerialName("managed"       ) @Optional val isManaged:       Boolean?          =null
 ,@SerialName("animated"      ) @Optional val isAnimated:      Boolean?          =null
)