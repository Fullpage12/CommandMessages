package me.fullpage.commandmessages.hooks;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PlaceholderAPIHook {

    public boolean getPlaceholderAPI() {
        PlaceholderAPIPlugin placeholderAPI;
        try {
            placeholderAPI = JavaPlugin.getPlugin(PlaceholderAPIPlugin.class);
        } catch (Exception e) {
            return false;
        }
        return placeholderAPI != null;
    }

    public String setPlaceholders(Player player, String str) {
        if (getPlaceholderAPI())
            return PlaceholderAPI.setPlaceholders(player, str);
        else return str;
    }

}
