package net.darkunscripted.PrestigeTokens;

import net.darkunscripted.PrestigeTokens.commands.PrestigeTokenCommand;
import net.darkunscripted.PrestigeTokens.files.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static DataManager data;

    @Override
    public void onEnable() {
        registerCommands();
        registerManagers();
        registerEvents();
        saveConfig();
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }

    public void registerCommands(){
        getCommand("prestigetoken").setExecutor(new PrestigeTokenCommand());
    }

    public void registerManagers(){
        this.data = new DataManager(this);
        data.saveDefaultConfig();

    }

    public void registerEvents(){

    }

}
