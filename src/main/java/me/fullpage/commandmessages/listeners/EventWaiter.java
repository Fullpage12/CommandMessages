package me.fullpage.commandmessages.listeners;

import me.fullpage.commandmessages.CommandMessages;
import me.fullpage.commandmessages.data.Config;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.logging.Level;

public class EventWaiter implements Listener {
    private final CommandMessages plugin;

    public EventWaiter(CommandMessages plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        String[] args = event.getMessage().split(" ");

        FileConfiguration config = Config.getConfigManager().getConfig();

        for (String key : config.getKeys(false)) {
            if (!args[0].replace("/", "").equals(key)) continue;
            event.setCancelled(true);
            if (config.get(key + ".message") == null) {
                plugin.getServer().getLogger().log(Level.WARNING, key + " in your config.yml does not have a message please add one.");
                continue;
            }

            List<String> message = config.getStringList(key + ".message");
            for (String msg : message) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            }






        }





    }

}
