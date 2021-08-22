package me.fullpage.commandmessages.data;

public class Config {

    private static final ConfigManager configManager = new ConfigManager("config.yml");

    public static ConfigManager getConfigManager() {
        return configManager;
    }

}
