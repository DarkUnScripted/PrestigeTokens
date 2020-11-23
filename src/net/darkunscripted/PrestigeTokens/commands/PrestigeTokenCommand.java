package net.darkunscripted.PrestigeTokens.commands;

import net.darkunscripted.PrestigeTokens.Main;
import net.darkunscripted.PrestigeTokens.utils.Utils;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PrestigeTokenCommand implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("prestigetoken.admin")){
                if(args.length < 3){
                    p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &e&lCommands:"));
                    p.sendMessage(Utils.chat("&b&l- &e/prestigetoken <set|remove|add|get> (name)"));
                }else if(args.length == 3){
                    try {
                        int amount = Integer.parseInt(args[2]);
                        OfflinePlayer user = null;
                        if(args[0].equalsIgnoreCase("set")){
                            for(OfflinePlayer player : p.getServer().getOfflinePlayers()){
                                if(player.getName().equalsIgnoreCase(args[1])){
                                    user = player;
                                }
                            }
                            if(user == null){
                                p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                            }else{
                                Main.data.getConfig().set(user.getUniqueId() + ".tokens", amount);
                                p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &aTokens setted!"));
                            }
                        }else if(args[0].equalsIgnoreCase("remove")){
                            for(OfflinePlayer player : p.getServer().getOfflinePlayers()){
                                if(player.getName().equalsIgnoreCase(args[1])){
                                    user = player;
                                }
                            }
                            if(user == null){
                                p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                            }else{
                                int tokens = 0;
                                if(Main.data.getConfig().contains(user.getUniqueId() + ".tokens")){
                                    tokens = Main.data.getConfig().getInt(user.getUniqueId() + ".tokens");
                                    if(tokens < amount){
                                        p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer does not have enough tokens!"));
                                    }else{
                                        Main.data.getConfig().set(user.getUniqueId() + ".tokens", (tokens - amount));
                                        p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &aTokens removed!"));
                                    }
                                }
                            }
                        }else if(args[0].equalsIgnoreCase("add")){
                            for(OfflinePlayer player : p.getServer().getOfflinePlayers()){
                                if(player.getName().equalsIgnoreCase(args[1])){
                                    user = player;
                                }
                            }
                            if(user == null){
                                p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                            }else{
                                int tokens = 0;
                                if(Main.data.getConfig().contains(user.getUniqueId() + ".tokens")){
                                    tokens = Main.data.getConfig().getInt(user.getUniqueId() + ".tokens");
                                    Main.data.getConfig().set(user.getUniqueId() + ".tokens", (tokens + amount));
                                    p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &aTokens added!"));
                                }
                            }
                        }else if(args[0].equalsIgnoreCase("get")){
                            for(OfflinePlayer player : p.getServer().getOfflinePlayers()){
                                if(player.getName().equalsIgnoreCase(args[1])){
                                    user = player;
                                }
                            }
                            if(user == null){
                                p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cPlayer not found!"));
                            }else{
                                int tokens = 0;
                                if(Main.data.getConfig().contains(user.getUniqueId() + ".tokens")){
                                    tokens = Main.data.getConfig().getInt(user.getUniqueId() + ".tokens");
                                    p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &a" + user.getName() + " has " + tokens));
                                }
                            }
                        }else{
                            p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &e&lCommands:"));
                            p.sendMessage(Utils.chat("&b&l- &e/prestigetoken <set|remove|add|get> (name)"));
                        }
                    }catch (NumberFormatException e){
                        p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cThe amount has to be a number"));
                    }
                }else if(args.length > 3){
                    p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &e&lCommands:"));
                    p.sendMessage(Utils.chat("&b&l- &e/prestigetoken <set|remove|add|get> (name)"));
                }
            }else{
                p.sendMessage(Utils.chat("&b&lPrestige Token &7>> &cYou have no permission to perform this command!"));
            }
        }
        return false;
    }

}
