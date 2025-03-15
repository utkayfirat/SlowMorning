package com.rp.slowMorning;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
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
        boolean skipNight = plugin.getConfig().getBoolean("skip_night_on_sleep", true);
        if (!skipNight) return;

        if (event.getBedEnterResult() != PlayerBedEnterEvent.BedEnterResult.OK) return;

        Player player = event.getPlayer();
        World world = player.getWorld();

        if (world.getTime() >= 12541 && world.getTime() <= 23458) {
            plugin.setNightSkipped(true);

            String message = ChatColor.RED + "Server: " + ChatColor.GOLD + player.getName() +
                    ChatColor.YELLOW + " has skipped the night by sleeping!";
            Bukkit.broadcastMessage(message);
        }
    }

}
