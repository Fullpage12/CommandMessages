package me.fullpage.commandmessages;

import me.fullpage.commandmessages.data.ConfigManager;
import me.fullpage.commandmessages.listeners.EventWaiter;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandMessages extends JavaPlugin {

    private static CommandMessages instance;

    public static CommandMessages getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        new ConfigManager("config.yml").saveDefaultConfig();
        new EventWaiter(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
