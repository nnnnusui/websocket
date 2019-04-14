package com.github.nnnnusui.discord.entity.permission

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Role(
  @SerialName("id"         ) val id:            Snowflake
 ,@SerialName("name"       ) val name:          String
 ,@SerialName("color"      ) val color:         Int       // TODO 16decimal color code
 ,@SerialName("hoist"      ) val isHoist:       Boolean
 ,@SerialName("position"   ) val position:      Int
 ,@SerialName("permissions") val permissions:   Int       // TODO permission bit set
 ,@SerialName("managed"    ) val isManaged:     Boolean
 ,@SerialName("mentionable") val isMentionable: Boolean
)