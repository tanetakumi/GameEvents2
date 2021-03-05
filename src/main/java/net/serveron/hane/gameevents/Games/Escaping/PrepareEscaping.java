package net.serveron.hane.gameevents.Games.Escaping;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.serveron.hane.gameevents.GameEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PrepareEscaping implements Listener {
    private final Escaping game;
    private final Player targetPlayer;

    public PrepareEscaping(Escaping game, Player player){
        this.game = game;
        this.targetPlayer = player;

        setStick(player,0,"ハンター初期位置");
        setStick(player,1,"捕獲者の牢獄位置");
    }

    public void deInit(){
        targetPlayer.getInventory().clear(0);
        targetPlayer.getInventory().clear(1);
    }

    private void setStick(Player player,int slot,String displayName){
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta im = item.getItemMeta();
        im.displayName(Component.text(displayName).color(TextColor.color(0xffd700)));
        item.setItemMeta(im);
        player.getInventory().setItem(slot,item);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if(player.getName().equals(targetPlayer.getName())){
            ItemMeta itemMeta = player.getInventory().getItemInMainHand().getItemMeta();
            if(itemMeta!=null && itemMeta.hasDisplayName()){
                if(itemMeta.displayName() == Component.text("ハンター初期位置").color(TextColor.color(0xffd700))){
                    e.setCancelled(true);
                    game.setCaptureSpawn(e.getBlock().getLocation().clone().add(0,1,0));
                    player.sendMessage(ChatColor.GOLD+"ハンター初期位置をセットしました");
                } else if(itemMeta.displayName() == Component.text("捕獲者の牢獄位置").color(TextColor.color(0xffd700))){
                    e.setCancelled(true);
                    game.setCaptureSpawn(e.getBlock().getLocation().clone().add(0,1,0));
                    player.sendMessage(ChatColor.GOLD+"捕獲者の牢獄位置をセットしました");
                }
            }
        }
    }
}
