package ru.metveylegenda.bairdropdiscordaddon;

import org.bukkit.plugin.java.JavaPlugin;
import ru.metveylegenda.bairdropdiscordaddon.listeners.AirDropStartListener;
import ru.metveylegenda.bairdropdiscordaddon.utils.Metrics;

public final class BAirDropDiscordAddon extends JavaPlugin {
    private static BAirDropDiscordAddon instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        getServer().getPluginManager().registerEvents(new AirDropStartListener(), this);

        int pluginId = 21127;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static BAirDropDiscordAddon getInstance() {
        return instance;
    }
}
