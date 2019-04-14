package com.github.nnnnusui.discord.enums

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import java.lang.IllegalArgumentException

@Serializable(with = Opcode.Companion::class)
enum class Opcode(val code: Int){
    DISPATCH(             0) /** dispatches an event (Receive) */
   ,HEARTBEAT(            1) /** used for ping checking (Send/Receive) */
   ,IDENTIFY(             2) /** used for client handshake (Send) */
   ,STATUS_UPDATE(        3) /** used to update the client status (Send) */
   ,VOICE_STATE_UPDATE(   4) /** used to join/move/leave voice channels (Send) */
   ,RESUME(               6) /** used to resume a closed connection (Send) */
   ,RECONNECT(            7) /** used to tell clients to reconnect to the gateway (Receive) */
   ,REQUEST_GUILD_MEMBERS(8) /** used to request guild members (Send) */
   ,INVALID_SESSION(      9) /** used to notify client they have an invalid session id (Receive) */
   ,HELLO(               10) /** sent immediately after connecting, contains heartbeat and server debug information (Receive) */
   ,HEARTBEAT_ACK(       11) /** sent immediately following a client heartbeat that was received (Receive) */
    ;

    @Serializer(forClass = Opcode::class)
    companion object: KSerializer<Opcode> {
        override val descriptor: SerialDescriptor = StringDescriptor.withName("Opcode <-> code: Int")
        override fun serialize(encoder: Encoder, obj: Opcode)
                { encoder.encodeInt(obj.code) }
        override fun deserialize(decoder: Decoder): Opcode {
            val num = decoder.decodeInt()
            return Opcode.values().find { it.code == num } ?: throw IllegalArgumentException("invalid code `$num` (${this::class})")
        }
    }
}