package com.github.nnnnusui.discord.entity

import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Emoji(
                                                  val id:              Snowflake?
 ,                                                val name:            String
 ,                                      @Optional val roles:           Array<Snowflake>?=null // Role object ids
 ,@SerialName( "user"          ) @Optional val creator:         User?            =null
 ,@SerialName( "require_colons") @Optional val isRequireColons: Boolean?         =null
 ,@SerialName( "managed"       ) @Optional val isManaged:       Boolean?         =null
 ,@SerialName( "animated"      ) @Optional val isAnimated:      Boolean?         =null
)