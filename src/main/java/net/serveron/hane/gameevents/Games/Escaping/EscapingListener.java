package net.serveron.hane.gameevents.Games.Escaping;

import net.kyori.adventure.text.Component;
import net.serveron.hane.gameevents.GameEvents;
import net.serveron.hane.gameevents.util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;

import java.util.ArrayList;
import java.util.List;

public class EscapingListener implements Listener {
    private final GameEvents plugin;
    private final Escaping game;
    private Timer timer;
    private Objective obj;

    public List<String> taggerList = new ArrayList<>();
    public List<String> escapeList = new ArrayList<>();

    public EscapingListener(GameEvents plugin,Escaping game){
        this.plugin = plugin;
        this.game = game;
    }

    public void startGame(){
        //イベントリスナーの登録
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        //タイマーの起動
        timer = new Timer(game.getGameTime()) {
            @Override
            public void finishTimer(){
                finishGame();
            }
        };
        timer.runTaskTimer(plugin,5,20);
        //
        for(Player p : Bukkit.getOnlinePlayers()){
            p.setGameMode(GameMode.ADVENTURE);
            Team team = plugin.mainScoreboard.getEntryTeam(p.getName());
            if(team!=null){
                if(team.getName().equals(tagger)){
                    taggerList.add(p.getName());
                    p.teleport(escapingInfo.taggerSpawn);
                    p.setGameMode(GameMode.ADVENTURE);
                    p.setWalkSpeed(0.2f);
                }
                else if(team.getName().equals(escape)){
                    p.getInventory().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
                    escapeList.add(p.getName());
                    p.setGameMode(GameMode.ADVENTURE);
                }
            }

            p.sendTitle(ChatColor.AQUA+"逃走中", "ゲームスタート",20,20,20);
        }
    }

    public void finishGame(){

    }

    public void endGame(){
        HandlerList.unregisterAll(this);
    }

}

