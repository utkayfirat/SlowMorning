package com.rp.slowMorning;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepTogether implements Listener {

    private SlowMorning plugin;

    public SleepTogether(SlowMorning slowMorning) {
        plugin = slowMorning;
    }

    @EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent event) {
        boolean st = plugin.getConfig().getBoolean("skip_night_on_sleep", true);
        if(!st) return;

        String playerName = event.getPlayer().getName();
        World world = event.getPlayer().getWorld();
        long time = world.getTime();
        if (time >= 12000 && time < 24000) {
            world.setTime(0);
            String message = ChatColor.DARK_RED+"Server: "+ ChatColor.GOLD + playerName + ChatColor.YELLOW + " has skipped the night by sleeping!";
            Bukkit.broadcastMessage(message);
        }
    }
}
