package net.serveron.hane.gameevents.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class TeamInfo {
    private final Team team;
    private final String teamName;
    public Location location;
    public Player King;
    public boolean respawnAble = true;
    public int count = 30;

    public TeamInfo(Team team){
        this.team = team;
        this.teamName = team.getName();
    }

    public Team getTeam() {
        return team;
    }

    public String getTeamName() {
        return teamName;
    }

    public int downCount(){
        count--;
        return count;
    }
    public int upCount(){
        count++;
        return count;
    }

    /** red:(0,65,0):リスON:リス回30:Hane3 */
    /** blue:(None):リスON:リス回30:Kingなし */
    public String ToTextForList(){
        String message = teamName;
        if(location!=null){
            String x = String.valueOf((int)location.getX());
            String y = String.valueOf((int)location.getY());
            String z = String.valueOf((int)location.getZ());
            message += ":("+x+","+y+","+z+")";
        }
        else message += ":(None)";
        //----------------------------------
        if(respawnAble) {
            message += ":リスON";
        }
        else {
            message += ":リスOFF";
        }
        message += ":リス回"+count;
        //-------------------------------------
        if(King==null){
            message += ":Kingなし";
        }
        else{
            message += ":"+King.getName();
        }
        return message;
    }
}