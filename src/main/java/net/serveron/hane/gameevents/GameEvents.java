package net.serveron.hane.gameevents;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.ArrayList;
import java.util.List;

public final class GameEvents extends JavaPlugin {

    public MainboardManager mainboardManager;
    public Scoreboard mainScoreboard;
    //public List<TeamInfo> teamInfoList = new ArrayList<>();

    TeamBattleListener teamBattleListener;
    public Tag tag;
    public Escaping escaping;
    public BlockRun blockRun;

    @Override
    public void onEnable() {
        // Plugin startup logic
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        mainScoreboard = manager.getMainScoreboard();
        mainboardManager = new MainboardManager(this);

        new GameCommand(this);
    }

    @Override
    public void onDisable() {

        if(tag!=null){
            tag.onFinish();
            tag = null;
        }
        if(blockRun!=null){
            blockRun.onFinish();
            blockRun = null;
        }
        if(escaping!=null){
            escaping.onFinish();
            escaping = null;
        }

    }

    public String currentGame(){
        if(tag!=null){
            return "tag";
        } else if(blockRun!=null){
            return "blockrun";
        } else if(escaping!=null){
            return "escaping";
        } else {
            return "none";
        }
    }
}
