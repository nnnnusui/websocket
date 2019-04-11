package com.github.nnnnusui.discord.enums

enum class CloseEventCode(val code: Int){
    UNKNOWN_ERROR(        4000)
   ,UNKNOWN_OPCODE(       4001)
   ,DECODE_ERROR(         4002)
   ,NOT_AUTHENTICATED(    4003)
   ,AUTHENTICATION_FAILED(4004)
   ,ALREADY_AUTHENTICATED(4005)
   ,INVALID_SEQ(          4007)
   ,RATE_LIMITED(         4008)
   ,SESSION_TIMEOUT(      4009)
   ,INVALID_SHARD(        4010)
   ,SHARDING_REQUIRED(    4011)
}