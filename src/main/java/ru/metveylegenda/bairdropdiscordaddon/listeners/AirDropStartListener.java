package ru.metveylegenda.bairdropdiscordaddon.listeners;

import java.awt.Color;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.by1337.bairdrop.api.event.AirDropStartEvent;
import ru.metveylegenda.bairdropdiscordaddon.BAirDropDiscordAddon;
import org.bukkit.event.Listener;
import ru.metveylegenda.bairdropdiscordaddon.utils.DiscordWebhook;

public class AirDropStartListener implements Listener {

    @EventHandler
    public void onAirDropStart(AirDropStartEvent event) {
        FileConfiguration config = BAirDropDiscordAddon.getInstance().getConfig();
        String airdropID = event.getAirDrop().getId();

        String embedTitle = config.getString(airdropID + ".airDropStartMessage.embed.title");
        String embedDescription = event.getAirDrop().replaceInternalPlaceholder(config.getString(airdropID + ".airDropStartMessage.embed.description"));
        Color embedColor = Color.decode("0x" + config.getString(airdropID + ".airDropStartMessage.embed.color"));

        String content = config.getString(airdropID + ".airDropStartMessage.content");

        DiscordWebhook webhook = new DiscordWebhook(config.getString(airdropID + ".webhook"));
                webhook.setContent(content);
                webhook.addEmbed(new DiscordWebhook.EmbedObject()
                        .setTitle(embedTitle)
                        .setDescription(embedDescription)
                        .setColor(embedColor));

        try {
            webhook.execute();
        } catch (Exception e) {
            BAirDropDiscordAddon.getInstance().getLogger().warning("Ошибка " + e);
        }
    }
}
