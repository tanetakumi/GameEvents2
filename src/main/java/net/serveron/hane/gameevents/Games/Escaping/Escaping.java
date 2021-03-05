package net.serveron.hane.gameevents.Games.Escaping;

import net.serveron.hane.gameevents.GameEvents;
import net.serveron.hane.gameevents.GameProgress;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

public class Escaping extends GameProgress {

    private final GameEvents plugin;

    private PrepareEscaping prepareEscaping;

    private Location taggerSpawn;
    private Location captureSpawn;
    private int gameTime = 0;
    private Team taggerTeam;
    private Team escapeTeam;

    public Escaping(GameEvents plugin){
        this.plugin = plugin;
    }

    public void initPrepare(Player player){
        prepareEscaping = new PrepareEscaping(this,player);
        plugin.getServer().getPluginManager().registerEvents(prepareEscaping, plugin);
    }

    public void deInitPrepare(){
        prepareEscaping.deInit();
        HandlerList.unregisterAll(prepareEscaping);//　このリスナーから登録されたすべてのリスナーの登録解除
        prepareEscaping = null;
    }

    public void startGame(){
    }

    public void finishGame(){

    }

    public boolean checkGameInfo(){
        return taggerSpawn != null && captureSpawn != null && gameTime != 0 && taggerTeam != null && escapeTeam != null;
    }



    public void setTaggerSpawn(Location taggerSpawn) {
        this.taggerSpawn = taggerSpawn;
    }

    public Location getTaggerSpawn() {
        return taggerSpawn;
    }

    public void setCaptureSpawn(Location captureSpawn){
        this.captureSpawn = captureSpawn;
    }

    public Location getCaptureSpawn(){
        return captureSpawn;
    }

    public void setGameTime(int gameTime){
        this.gameTime = gameTime;
    }

    public int getGameTime(){
        return gameTime;
    }

    public void setTaggerTeam(Team taggerTeam){
        this.taggerTeam = taggerTeam;
    }

    public void setEscapeTeam(Team escapeTeam){
        this.escapeTeam = escapeTeam;
    }
}
