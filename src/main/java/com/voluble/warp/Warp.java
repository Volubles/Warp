package com.voluble.warp;

import com.voluble.warp.commands.SetWarpCommand;
import com.voluble.warp.commands.WarpCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Warp extends JavaPlugin {
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    @Override
    public void onEnable() {
        loadConfig();
        WarpCommand warpCommand = new WarpCommand(this);
        SetWarpCommand setWarpCommand = new SetWarpCommand(this);

        getCommand("warp").setExecutor(warpCommand);
        getCommand("setwarp").setExecutor(setWarpCommand);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
