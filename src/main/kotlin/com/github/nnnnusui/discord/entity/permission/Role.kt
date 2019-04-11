package com.github.nnnnusui.discord.entity.permission

import com.github.nnnnusui.discord.entity.value.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @formatter:off
@Serializable
data class Role(
                                    val id:            Snowflake
 ,                                  val name:          String
 ,                                  val color:         Int // TODO 16decimal color code
 ,@SerialName("hoist"      ) val isHoist:       Boolean
 ,                                  val position:      Int
 ,                                  val permissions:   Int // TODO permission bit set
 ,@SerialName("managed"    ) val isManaged:     Boolean
 ,@SerialName("mentionable") val isMentionable: Boolean
)