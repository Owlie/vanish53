package net.econcraft.vanish53;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command53 implements CommandExecutor {
	
	///////////////////////////////////////
	// PUBLIC MEMBERS
	///////////////////////////////////////
	
	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////
	private Main53 plugin;
	private User53 user;
	
	///////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////
	public Command53(Main53 main) {
		this.plugin = main;
		this.user = null;
	}
	
	///////////////////////////////////////
	// PUBLIC METHODS
	///////////////////////////////////////
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("vanish")) {
			int numArgs = args.length;
			if(sender instanceof Player) {
				this.user = plugin.grabUser((Player)sender);
				if(this.user != null) {
					
					
					if(numArgs == 0) {
						this.user.sayState();
						
						
					} else if(numArgs == 1) {
						String toggle = args[0].toLowerCase();
						if(toggle.equalsIgnoreCase("on") || toggle.equalsIgnoreCase("off")) {
							this.plugin.getHandler().vanishUser(this.user, toggle, "d");
						} else if(toggle.equalsIgnoreCase("reload")) {
							// RELOAD PLAYER CONFIG
							this.user.config.loadConfig();
							this.user.tell(ChatColor.GRAY + "Configuration successfully reloaded!");
						} else if(toggle.equalsIgnoreCase("save")) {
							// TODO: SAVE CONFIG
						} else {
							this.invalidSyntax();
						}
					
					
					} else if(numArgs == 2) {
						String toggle = args[0];
						String method = args[1];
						if(toggle.equalsIgnoreCase("on") || toggle.equalsIgnoreCase("off")) {
							if(method.equalsIgnoreCase("s") || method.equalsIgnoreCase("l")) {
								this.plugin.getHandler().vanishUser(this.user, toggle, method);
							} else {
								this.invalidSyntax();
							}
						} else {
							this.invalidSyntax();
						}
						
					// /vanish on morph dragon
					} else if(numArgs == 3) {
						String toggle = args[0];
						String method = args[1];
						String effect = args[2];
						if(toggle.equalsIgnoreCase("on") || toggle.equalsIgnoreCase("off")) {
							if(method.equalsIgnoreCase("morph")) {
								this.plugin.getHandler().vanishUser(this.user, toggle, "s");
								this.user.launchEffects(toggle, effect);
							}
						}
					}
				} else {
					// TODO: USER COULD NOT BE LOADED
				}
			}
		}
		return true;
	}
	
	///////////////////////////////////////
	// PRIVATE METHODS
	///////////////////////////////////////
	private void invalidSyntax() {
		this.user.tell(ChatColor.RED + "INVALID SYNTAX!");
	}
}