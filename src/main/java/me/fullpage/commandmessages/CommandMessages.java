package me.fullpage.commandmessages;

import me.fullpage.commandmessages.data.ConfigManager;
import me.fullpage.commandmessages.hooks.PlaceholderAPIHook;
import me.fullpage.commandmessages.listeners.EventWaiter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

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

        if (new PlaceholderAPIHook().getPlaceholderAPI())
            getLogger().log(Level.INFO, "Successfully hooked into PlaceholderAPI");

    }

}
