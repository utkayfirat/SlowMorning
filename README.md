# Showcase
<img src="https://raw.githubusercontent.com/utkayfirat/SlowMorning/refs/heads/main/gameplay.png"><br>

# SlowMorning
<i>SlowMorning is a plugin that slows down the passage of time in the Minecraft world. You can configure the duration of the morning and night periods, and the plugin will make time pass slower during specific intervals. This plugin shows the current time to players via a BossBar that updates every second, providing visual feedback on the passage of time.</i>

# Features:
- Time Slowing: Customize the morning and night durations to slow down time in the Minecraft world.
- Dynamic Time: Adjustable slowdown factors for both the day and night periods.
- BossBar Display: Shows the current time to players on a dynamically updated BossBar.
- Automatic BossBar Setup for New Players: Each new player who joins the server will automatically have the BossBar set up displaying the current time.
- Night Skip Feature: If enabled in the configuration, a single player sleeping will skip the night for the entire server.

# Configuration:
- clock_name: Custom name for the BossBar that will display the current time.
- morning_duration: Duration of the morning period.
- night_duration: Duration of the night period.
- skip_night_on_sleep: If set to true, a single player sleeping will skip the night.

# config.yml
<code>clock_name: "ReleasePlay Saat"  # Bar Name<br>
 morning_duration: 30  # 30 mins<br>
 night_duration: 15  # 15 mins<br>
 skip_night_on_sleep: true  # Enables night skipping when a player sleeps
</code>
