package me.fullpage.commandmessages.data;

import me.fullpage.commandmessages.CommandMessages;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

@SuppressWarnings({"unused", "WeakerAccess", "ResultOfMethodCallIgnored"})
public class ConfigManager {

    /**
     * An instance of the Main Class
     */
    private final CommandMessages plugin = CommandMessages.getInstance();

    /**
     * The name of the file you are working with
     */
    private final String fileName;

    /**
     * The File in which the file is apart of
     */
    private final File configFile;

    /**
     * The FileConfiguration that allows you to work with values in the inputted file
     */
    private FileConfiguration fileConfiguration;

    /**
     * @param fileName is the name of the file that you wish to get
     */
    public ConfigManager(String fileName) {
        this.fileName = fileName;
        File dataFolder = plugin.getDataFolder();
        if (dataFolder == null)
            throw new IllegalStateException();
        this.configFile = new File(plugin.getDataFolder(), fileName);
    }

    /**
     * Use this to reload the config file
     */
    public void reloadConfig() {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
        InputStream defConfigStream = plugin.getResource(fileName);
        if (defConfigStream != null) {
            YamlConfiguration defConfig
                    = YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream));
            fileConfiguration.setDefaults(defConfig);
        }

    }

    /**
     * @return will return the FileConfiguration for the inputted file
     */
    public FileConfiguration getConfig() {
        if (fileConfiguration == null) {
            this.reloadConfig();
        }
        return fileConfiguration;
    }

    /**
     * Use this to save the config after editing it plugin-side
     */
    public void saveConfig() {
        if (fileConfiguration != null && configFile != null) {
            try {
                getConfig().save(configFile);
            } catch (IOException ex) {
                plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
            }
        }
    }

    /**
     * Use this to save the default config to the data folder for the plugin
     */
    public void saveDefaultConfig() {
        File configf = new File(plugin.getDataFolder(), fileName);
        if (!configf.exists()) {
            configf.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }
        FileConfiguration config = new YamlConfiguration();
        try {
            config.load(configf);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
