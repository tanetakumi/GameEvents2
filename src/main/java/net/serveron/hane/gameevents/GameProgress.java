package net.serveron.hane.gameevents;

import org.bukkit.entity.Player;

public abstract class GameProgress {

    private final GameEvents plugin;
    //Progress
    private int progress = 0;

    abstract public void initPrepare(Player player);

    abstract public void deInitPrepare();

    abstract public boolean checkGameInfo();

    abstract public void startGame();

    abstract public void finishGame();

    public GameProgress(GameEvents plugin){
        this.plugin = plugin;
        //Timer timer = new Timer(plugin);
    }

    public boolean prepare(Player player){
        if(progress==0){
            initPrepare(player);
            progress = 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean ready(){
        if(progress==1){
            if(checkGameInfo()){
                deInitPrepare();
                progress = 2;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean start(){
        if(progress==2){
            startGame();
            progress = 3;
            return true;
        } else {
            return false;
        }
    }

    public void finish(){
        if(progress < 2){
            deInitPrepare();
        }
        if(progress == 3){
            finishGame();
        }
    }
}
