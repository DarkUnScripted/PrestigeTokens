package net.darkunscripted.PrestigeTokens.commands;

import net.darkunscripted.PrestigeTokens.Main;
import net.darkunscripted.PrestigeTokens.utils.Utils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.IOException;

public class PrestigeTokenCommand implements CommandExecutor, Listener {

    private Main plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s.hasPermission("prestigetoken.admin")){
            if(args.length < 2) {
                s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &e&lCommands:"));
                s.sendMessage(Utils.chat("&b&l- &e/prestigetoken <set|remove|add|get> (name)"));
            }else if(args.length == 2){
                OfflinePlayer user = null;
                if(args[0].equalsIgnoreCase("get")) {
                    for (OfflinePlayer player : s.getServer().getOfflinePlayers()) {
                        if (player.getName().equalsIgnoreCase(args[1])) {
                            user = player;
                        }
                    }
                    if (user == null) {
                        s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                    } else {
                        int tokens = 0;
                        if (plugin.getManager().playerscfg.contains(user.getUniqueId() + ".tokens")) {
                            tokens = plugin.getManager().playerscfg.getInt(user.getUniqueId() + ".tokens");
                            s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &a" + user.getName() + " has " + tokens + " prestige tokens!"));
                        }
                    }
                }else{
                    s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &e&lCommands:"));
                    s.sendMessage(Utils.chat("&b&l- &e/prestigetoken <set|remove|add|get> (name)"));
                }
            }else if(args.length == 3){
                try {
                    int amount = Integer.parseInt(args[2]);
                    OfflinePlayer user = null;
                    if(args[0].equalsIgnoreCase("set")){
                        for(OfflinePlayer player : s.getServer().getOfflinePlayers()){
                            if(player.getName().equalsIgnoreCase(args[1])){
                                user = player;
                            }
                        }
                        if(user == null){
                            s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                        }else{
                            plugin.getManager().playerscfg.set(user.getUniqueId() + ".tokens", amount);
                            try {
                                plugin.getManager().playerscfg.save(plugin.getManager().playersfile);
                                s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &aTokens setted!"));
                            }catch (IOException e){
                                s.sendMessage(Utils.chat("&cAn error has occured"));
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("remove")){
                        for(OfflinePlayer player : s.getServer().getOfflinePlayers()){
                            if(player.getName().equalsIgnoreCase(args[1])){
                                user = player;
                            }
                        }
                        if(user == null){
                            s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                        }else{
                            int tokens = 0;
                            if(plugin.getManager().playerscfg.contains(user.getUniqueId() + ".tokens")){
                                tokens = plugin.getManager().playerscfg.getInt(user.getUniqueId() + ".tokens");
                                if(tokens < amount){
                                    s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer does not have enough tokens!"));
                                }else{
                                    plugin.getManager().playerscfg.set(user.getUniqueId() + ".tokens", (tokens - amount));
                                    try {
                                        plugin.getManager().playerscfg.save(plugin.getManager().playersfile);
                                        s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &aTokens removed!"));
                                    }catch (IOException e){
                                        s.sendMessage(Utils.chat("&cAn error has occured"));
                                    }
                                }
                            }
                        }
                    }else if(args[0].equalsIgnoreCase("add")){
                        for(OfflinePlayer player : s.getServer().getOfflinePlayers()){
                            if(player.getName().equalsIgnoreCase(args[1])){
                                user = player;
                            }
                        }
                        if(user == null){
                            s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                        }else{
                            int tokens = 0;
                            if(plugin.getManager().playerscfg.contains(user.getUniqueId() + ".tokens")){
                                tokens = plugin.getManager().playerscfg.getInt(user.getUniqueId() + ".tokens");
                                plugin.getManager().playerscfg.set(user.getUniqueId() + ".tokens", (tokens + amount));
                                try {
                                    plugin.getManager().playerscfg.save(plugin.getManager().playersfile);
                                    s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &aTokens added!"));
                                }catch (IOException e){
                                    s.sendMessage(Utils.chat("&cAn error has occured"));
                                }
                            }
                        }
                    }else{
                        s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &e&lCommands:"));
                        s.sendMessage(Utils.chat("&b&l- &e/prestigetoken <set|remove|add|get> (name)"));
                    }
                }catch (NumberFormatException e){
                    s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cThe amount has to be a number"));
                }
            }else if(args.length > 3){
                s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &e&lCommands:"));
                s.sendMessage(Utils.chat("&b&l- &e/prestigetoken <set|remove|add|get> (name)"));
            }
        }else{
            s.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cYou have no permission to perform this command!"));
        }
        return false;
    }

}
