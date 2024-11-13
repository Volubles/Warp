package com.voluble.warp.commands;

import com.voluble.warp.Warp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Set;

public class WarpCommand implements CommandExecutor {

	private SetWarpCommand setWarpCommand;
	private Warp plugin;

	public WarpCommand(Warp plugin) {
		this.setWarpCommand = setWarpCommand;
		this.plugin = plugin;
		plugin.getCommand("warp").setExecutor(this);
	}

	private String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command");
			return false;
		}
		Player player = (Player) sender;

		if (args.length == 0) {
			Set<String> warpNames = plugin.getConfig().getConfigurationSection("warps").getKeys(false);

			if (warpNames.isEmpty()) {
				sender.sendMessage(color("&4There is no warps"));
			} else {

				String warpList = String.join(", ", warpNames);
				sender.sendMessage(color("&3Available warps:"));
				sender.sendMessage(warpList);
			}
			return true;
		}

		String name = args[0].toLowerCase();
		if (plugin.getConfig().getConfigurationSection("warps").get(name) == null) {
			player.sendMessage(color("&cNo warps with that name"));
			return false;
		}

		Location location;
		double getX = plugin.getConfig().getDouble("warps." + name + ".X");
		double getY = plugin.getConfig().getDouble("warps." + name + ".Y");
		double getZ = plugin.getConfig().getDouble("warps." + name + ".Z");
		float getYaw = (float) plugin.getConfig().getDouble("warps." + name + ".YAW");
		float getPitch = (float) plugin.getConfig().getDouble("warps." + name + ".PITCH");
		String getWorld = plugin.getConfig().getString("warps." + name + ".World");

		location = new Location(Bukkit.getWorld(getWorld), getX, getY, getZ, getYaw, getPitch);
		player.teleport(location);
		player.sendMessage(color("&3You have been teleported to the warp " + name));

		return true;
	}
}
