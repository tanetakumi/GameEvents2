package net.serveron.hane.gameevents.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
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
            Bukkit.getOnlinePlayers().forEach(p -> p.sendActionBar(Component.text("残り時間 "+minute+"分"+second+"秒")));
        }
        else {
            this.cancel();
            finishTimer();
        }
    }
    public void finishTimer(){

    }
}


//Bukkit.getOnlinePlayers().forEach(p -> p.sendActionBar(Component.text("残り時間 "+minute+"分"+second+"秒")));