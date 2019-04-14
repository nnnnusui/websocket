package com.github.nnnnusui.discord.entity

import com.github.nnnnusui.discord.entity.channel.Channel
import com.github.nnnnusui.discord.entity.guild.Guild
import com.github.nnnnusui.discord.entity.user.User
import com.github.nnnnusui.discord.entity.value.ISO8601Serializer
import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

// @formatter:off
@Serializable
data class Invite(
  @SerialName("code"                      )           val code:                     String  // unique ID
 ,@SerialName("guild"                     ) @Optional val guild:                    Guild?  =null // TODO partial
 ,@SerialName("channel"                   )           val channel:                  Channel // TODO partial
 ,@SerialName("approximate_presence_count") @Optional val approximatePresenceCount: Int?    =null // count online members
 ,@SerialName("approximate_member_count"  ) @Optional val approximateMemberCount:   Int?    =null // count total members
){
    @Serializable
    data class Metadata(
      @SerialName("inviter"   ) val inviter:     User    // creator
     ,@SerialName("uses"      ) val uses:        Int     // number of times this invite has been used
     ,@SerialName("max_uses"  ) val maxUses:     Int     // max number of times this invite can be used
     ,@SerialName("max_age"   ) val maxAge:      Int     // duration until expires
     ,@SerialName("temporary" ) val isTemporary: Boolean
     ,@Serializable(with = ISO8601Serializer::class)
      @SerialName("created_at") val createdAt:   Date
     ,@SerialName("revoked"   ) val isRevoked:   Boolean
    )
}