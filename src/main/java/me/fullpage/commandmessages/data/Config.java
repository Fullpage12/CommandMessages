package me.fullpage.commandmessages.data;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private static final ConfigManager configManager = new ConfigManager("config.yml");
    private static FileConfiguration config = configManager.getConfig();

    public static ConfigManager getConfigManager() {
        return configManager;
    }



}
