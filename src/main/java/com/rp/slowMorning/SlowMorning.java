package com.rp.slowMorning;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public final class SlowMorning extends JavaPlugin {

    private int morningDuration, nightDuration;
    private Map<Player, BossBar> playerBossBars = new HashMap<>();
    private String clockName;
    private int counter = 0;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        morningDuration = getConfig().getInt("morning_duration", 30);
        nightDuration = getConfig().getInt("night_duration", 15);
        clockName = getConfig().getString("clock_name", "ReleasePlay Saat");

        int morningSlowdownFactor = (morningDuration * 60) / 300;
        int nightSlowdownFactor = (nightDuration * 60) / 420;

        new BukkitRunnable() {

            @Override
            public void run() {
                World world = Bukkit.getWorlds().get(0);
                long time = world.getTime();

                int hours = (int) ((time / 1000.0) * 1.5) % 24;
                int minutes = (int) ((time % 1000) / 1000.0 * 60);

                String formattedTime = String.format("%02d:%02d", hours, minutes);

                String message = ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "" +clockName+" - " + ChatColor.LIGHT_PURPLE + formattedTime;

                for (Player player : Bukkit.getOnlinePlayers()) {

                    if (player.getWorld().getName().equals("world_the_end")) {
                        removeBossBar(player);
                        continue;
                    }

                    updateOrCreateBossBar(player, message);
                }

                updateWorldTime(world, time, morningSlowdownFactor, nightSlowdownFactor);
                counter++;
            }
        }.runTaskTimer(this, 0L, 20L);
    }

    private void updateOrCreateBossBar(Player player, String message) {
        BossBar existingBar = getExistingBossBarForPlayer(player);
        if (existingBar == null) {
            BossBar bossBar = Bukkit.createBossBar(message, BarColor.PURPLE, BarStyle.SEGMENTED_10);
            bossBar.setProgress(1.0);
            bossBar.addPlayer(player);
            saveBossBarForPlayer(player, bossBar);
        } else {
            existingBar.setTitle(message);
        }
    }

    private void removeBossBar(Player player) {
        BossBar existingBar = getExistingBossBarForPlayer(player);
        if (existingBar != null) {
            existingBar.removePlayer(player);
            playerBossBars.remove(player);
        }
    }

    private void updateWorldTime(World world, long time, int morningSlowdownFactor, int nightSlowdownFactor) {
        if (time >= 0 && time < 6000) {
            if (counter % morningSlowdownFactor == 0) {
                world.setTime(time + 1);
            }
        } else if (time >= 18000 && time < 24000) {
            if (counter % nightSlowdownFactor == 0) {
                world.setTime(time + 1);
            }
        } else {
            world.setTime(time + 10);
        }
    }

    private BossBar getExistingBossBarForPlayer(Player player) {
        return playerBossBars.get(player);
    }

    private void saveBossBarForPlayer(Player player, BossBar bossBar) {
        playerBossBars.put(player, bossBar);
    }

    @Override
    public void onDisable() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            removeBossBar(player);
        }
    }
}
