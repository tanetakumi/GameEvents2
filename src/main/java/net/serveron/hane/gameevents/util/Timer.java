package net.serveron.hane.gameevents.util;

import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer extends BukkitRunnable {
    private int time;
    public Timer(int time){
        this.time = time;
    }
    @Override
    public void run(){
        time--;
        if(time>0) {
            int minute = time/60;
            int second = time%60;
            for(Player p : Bukkit.getOnlinePlayers()){
                p.sendActionBar(Component.text("残り時間 "+minute+"分"+second+"秒"));
            }
        }
        else {
            deinitActionbar();
            triggerAction();
        }
    }
    public void deinitActionbar(){
        this.cancel();
    }
    public void triggerAction(){

    }

}