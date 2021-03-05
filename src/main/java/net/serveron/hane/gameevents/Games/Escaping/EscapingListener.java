package net.serveron.hane.gameevents.Games.Escaping;

import net.serveron.hane.gameevents.util.Game;
import org.bukkit.event.Listener;

public class EscapingListener implements Listener {
    private final Escaping game;
    private Game timer;

    public EscapingListener(Escaping game){
        this.game = game;
        timer = new Game(game.getGameTime()){
            @Override
            public void timerFinish(){

            }
        }.runTaskTimer(plugin,)


    }

}

