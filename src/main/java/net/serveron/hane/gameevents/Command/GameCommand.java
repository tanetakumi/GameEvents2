package net.serveron.hane.gameevents.Command;

import net.kyori.adventure.text.Component;
import net.serveron.hane.gameevents.GameEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameCommand implements CommandExecutor, TabCompleter {
    private final GameEvents plugin;

    public GameCommand(GameEvents plugin) {
        this.plugin = plugin;
        plugin.getCommand("cg").setExecutor(this);
    }

    private void Notify(String text) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if(p.hasPermission("op")) {
                p.sendMessage(ChatColor.AQUA + text);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1) {
                plugin.runAsyncDiscord(args[0]);
            } else {
                System.out.println("Console　引数指定エラー");
            }
        } else if (sender instanceof BlockCommandSender) {
            if (args.length == 1) {
                //plugin.runAsyncDiscord(args[0]);
            } else {
                System.out.println("Command Block　引数指定エラー");
            }
        } else if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 1) {
                if (player.hasPermission("cg")) {
                    switch (args[0].toLowerCase()) {
                        case "teambattle":
                            break;
                        case "tag":
                            break;
                        case "escaping":
                            break;
                        case "blockrun":
                            break;
                    }

                } else {
                    player.sendMessage(Component.text("コマンドの引数が違うよ"));
                }
            }
        }
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String command, String[] args) {
        List<String> autoComplete = new ArrayList<>();
        if(sender.hasPermission("cg")){

            if (args.length == 1){//一段目
                autoComplete.addAll(Arrays.asList("tag","teambattle","blockrun","escaping"));
            }
            else if(args.length >1){//二段目
                if(args[0].equalsIgnoreCase("tag") ||args[0].equalsIgnoreCase("escaping") || args[0].equalsIgnoreCase("teambattle") ){
                    if(args.length == 2){
                        autoComplete.addAll(Arrays.asList("1.prepare","2.set","3.ready","4.start","5.finish"));
                    }
                    else if(args[1].equalsIgnoreCase("set")){
                        if(args.length == 3){
                            autoComplete.addAll(Arrays.asList("600","900","1200","1800"));
                        } else if(args.length == 4){
                            //autoComplete.addAll(plugin.mainboardManager.getCurrentTeams());
                        } else if(args.length == 5){
                            //autoComplete.addAll(plugin.mainboardManager.getCurrentTeams());
                            //autoComplete.remove(args[3]);
                        }
                    }
                } else if(args[0].equalsIgnoreCase("blockrun")){
                    if(args.length == 2){
                        autoComplete.addAll(Arrays.asList("prepare","set","construct","deconstruct","ready","start","finish"));
                    } else if(args.length == 3){
                        if(args[1].equalsIgnoreCase("set")){
                            //autoComplete.addAll(plugin.mainboardManager.getCurrentTeams());
                        } else if(args[1].equalsIgnoreCase("construct")){
                            autoComplete.addAll(Arrays.asList("30","35","40"));
                        }
                    }
                }
            }
        }
        //文字比較と削除-----------------------------------------------------
        //Collections.sort(autoComplete);
        autoComplete.removeIf(str -> !str.startsWith(args[args.length - 1]));
        //------------------------------------------------------
        return autoComplete;
    }
}
