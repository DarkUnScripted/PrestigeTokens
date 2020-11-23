package net.darkunscripted.PrestigeTokens;

import net.darkunscripted.PrestigeTokens.commands.PrestigeTokenCommand;
import net.darkunscripted.PrestigeTokens.files.DataManager;
import net.darkunscripted.PrestigeTokens.utils.Utils;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    private DataManager cfgm;
    @Override
    public void onEnable() {
        loadConfigManager();
        getCommand("prestigetoken").setExecutor(new PrestigeTokenCommand());

        getServer().getConsoleSender().sendMessage(Utils.chat("&a\n\nExoPrestigeTokens has been enabled"));
        loadConfig();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(Utils.chat("&c\n\nExoPrestigeTokens has been Disabled"));
    }

    public void loadConfigManager(){
        cfgm = new DataManager();
        cfgm.setup();
        cfgm.savePlayers();
        cfgm.reloadPlayers();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public DataManager getManager(){
        return cfgm;
    }

}
