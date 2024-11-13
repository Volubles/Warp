package com.voluble.warp.commands;

import com.voluble.warp.Warp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

	private Warp plugin;
	private WarpCommand warpCommand;

	public SetWarpCommand(Warp plugin) {
		this.plugin = plugin;
		this.warpCommand = warpCommand;
	}

	private String Color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage(Color("&4Only players can execute this command."));
			return true;
		}
		Player player = (Player) sender;
		if (!player.hasPermission("warp.set")) {
		}

		if (args.length == 0) {
			sender.sendMessage(Color("Please input a name for the warp"));
			return true;
		}

		String name = args[0].toLowerCase();

		if (plugin.getConfig().get(name) != null) {
			player.sendMessage("There is already a warp with that name");
		}
		Location location = player.getLocation();
		plugin.getConfig().set("warps." + name + ".World", location.getWorld().getName());
		plugin.getConfig().set("warps." + name + ".X", location.getX());
		plugin.getConfig().set("warps." + name + ".Y", location.getY());
		plugin.getConfig().set("warps." + name + ".Z", location.getZ());
		plugin.getConfig().set("warps." + name + ".PITCH", location.getPitch());
		plugin.getConfig().set("warps." + name + ".YAW", location.getYaw());
		plugin.saveConfig();
		player.sendMessage(Color("&eWarp set!"));
		return true;
	}
}
