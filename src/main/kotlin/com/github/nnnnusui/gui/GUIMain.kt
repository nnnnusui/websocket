package com.github.nnnnusui.gui

import com.github.nnnnusui.DisKotd
import com.github.nnnnusui.TOKEN
import com.github.nnnnusui.discord.command.UpdateVoiceState
import com.github.nnnnusui.discord.entity.guild.Guild
import com.github.nnnnusui.discord.entity.value.Snowflake
import com.github.nnnnusui.discord.event.GuildCreate
import com.github.nnnnusui.discord.event.VoiceServerUpdate
import com.github.nnnnusui.discord.voice.Gateway
import com.github.nnnnusui.websocket.inclusion.LoggingListener
import javafx.beans.property.*
import javafx.collections.ListChangeListener
import javafx.scene.layout.BorderPane
import kotlinx.coroutines.delay
import tornadofx.*
import java.net.http.HttpClient

fun main(){ launch<GUIMain>() }

class GUIMain: App(MyView::class, Style::class)
class Style: Stylesheet(){
    init {
        root {
            prefHeight = 600.px
            prefWidth  = 600.px
        }
    }
}
class MyView: View(){
    val client = Client
    val guilds get() = client.guilds.map { GuildProperty(it) }.observable()
    val persons = listOf(Person("oa", "aoa"), Person("oa", "uiu")).observable()
//    val model = GuildViewModel()
    override val root = Form()

    class Field{
        var guildId   = "".toProperty()
        var channelId = "".toProperty()
    }
    init {
        val field = Field()
        client.start()
        Thread.sleep(100)
        with(root) {
                fieldset {
                    field { textfield { bind(field.guildId) } }
                    field { textfield { bind(field.channelId) } }
                }
                button {
                    setOnAction {
                        client.invideVoiceChannel(field.guildId.value, field.channelId.value)
                    }
                    disableProperty().bind(
                        field.guildId.isNull
                     or field.channelId.isNull
                    )
                }
//            bottom { button { setOnAction { center =  } } }
        }
    }
}
class Person(name: String? = null, title: String? = null) {
    val nameProperty = SimpleStringProperty(this, "name", name)
    var name by nameProperty

    val titleProperty = SimpleStringProperty(this, "title", title)
    var title by titleProperty
}
class GuildProperty(guild: Guild){
    val nameProperty = guild.name.toProperty()
    val idProperty   = guild.id.raw.toProperty()
    val channelProperty = guild.channels?.toList().toProperty()
}
class ClientProperty(client: Client){
    val guildsProperty = SimpleObjectProperty(this, "guilds", client.guilds)
}
class GuildViewModel: ItemViewModel<Guild>(){
    val name = bind(Guild::name)
}
object Client{
    val client = lazy { DisKotd.create(TOKEN) { addHandler(this@Client) } }.value
    val guilds = mutableListOf<Guild>().observable()
    fun start(){
        client
    }
    fun guildCreate(event: GuildCreate){
        val guild = event.guild
        println("guildCreate: ${guild.id} (${guild.name})")
        guilds.add(guild)
    }
    fun voiceServerUpdate(event: VoiceServerUpdate){
        val gateway = Gateway(event, client.logger)
        gateway.run()
    }
    fun invideVoiceChannel(guildId: String, channelId: String){
        client.gateway.sendCommand(UpdateVoiceState(
            Snowflake(guildId)
           ,Snowflake(channelId)
           ,false
           , false
        ))
    }

}