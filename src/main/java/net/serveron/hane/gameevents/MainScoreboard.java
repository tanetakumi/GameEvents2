package net.serveron.hane.gameevents;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class MainScoreboard {
    private final Scoreboard mainScoreboard;

    public MainScoreboard(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        mainScoreboard = manager.getMainScoreboard();
    }

    public List<String> getCurrentTeams() {
        List<String> list = new ArrayList<>();
        mainScoreboard.getTeams().forEach(t -> list.add(t.getName()));
        return list;
    }

    public void clearScoreboard(){
        mainScoreboard
    }
}
