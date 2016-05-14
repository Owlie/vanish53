package net.econcraft.vanish53;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Handler53 {
	
	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////
	private Main53 plugin;
	
	///////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////
	public Handler53(Main53 main) {
		this.plugin = main;
	}
	
	///////////////////////////////////////
	// PUBLIC METHODS
	///////////////////////////////////////
	
	public void vanishUser(User53 user, String toggle, String method) {
		//this.plugin.getServer().broadcastMessage(user.myName() + " " + toggle + " " + method);
		if(toggle.equalsIgnoreCase("on")) {
			if(user.isVanished()) {
				user.tell(ChatColor.LIGHT_PURPLE + "You are already " + ChatColor.GREEN + "VANISHED!");
			} else {
				if(method.equalsIgnoreCase("s")) {
					user.hideMe();
				} else if(method.equalsIgnoreCase("l")) {
					user.hideMe();
					user.launchEffects(toggle);
				} else {
					// Use default vanish method
					if(user.config.getBool(user.config.defaultvanishPath)) {
						this.vanishUser(user, toggle, "s");
					} else {
						this.vanishUser(user, toggle, "l");
					}
				}
			}
			
			
		} else if(toggle.equalsIgnoreCase("off")) {
			if(!user.isVanished()) {
				user.tell(ChatColor.LIGHT_PURPLE + "You are already " + ChatColor.GREEN + "VISIBLE!");
			} else {
				if(method.equalsIgnoreCase("s")) {
					user.showMe();
				} else if(method.equalsIgnoreCase("l")) {
					user.showMe();
					user.launchEffects(toggle);
				} else {
					// Use default vanish method
					if(user.config.getBool(user.config.defaultvanishPath)) {
						this.vanishUser(user, toggle, "s");
					} else {
						this.vanishUser(user, toggle, "l");
					}
				}
			}
		}
	}
	
	public void hideUser(User53 user) {
		for(Player player : this.plugin.getServer().getOnlinePlayers()) {
			User53 aUser = this.plugin.grabUser(player);
			if(aUser == null) {
				player.hidePlayer(user.getPlayer());
			} else {
				if(aUser.perms.seePerm && aUser != user) {
					aUser.tell(ChatColor.DARK_GRAY + user.myName() + " is now " + ChatColor.RED + "HIDDEN" + ChatColor.DARK_GRAY + " from all players");
				} else {
					user.tell(ChatColor.LIGHT_PURPLE + "You are now " + ChatColor.RED + "HIDDEN" + ChatColor.LIGHT_PURPLE + " from all players.");
				}
			}
		}
	}
	
	public void showUser(User53 user) {
		for(Player player : this.plugin.getServer().getOnlinePlayers()) {
			User53 aUser = this.plugin.grabUser(player);
			if(aUser == null) {
				player.showPlayer(user.getPlayer());
			} else {
				if(aUser.perms.seePerm && aUser != user) {
					aUser.tell(ChatColor.DARK_GRAY + user.myName() + " is now " + ChatColor.GREEN + "VISIBLE" + ChatColor.DARK_GRAY + " to all players");
				} else {
					user.tell(ChatColor.LIGHT_PURPLE + "You are now " + ChatColor.GREEN + "VISIBLE" + ChatColor.LIGHT_PURPLE + " to all players.");
				}
			}
		}
	}
	
	public void hideVanished(User53 user) {
		if(!plugin.getVanished().isEmpty()) {
			List<User53> vanishedUsers = plugin.getVanished();
			for(User53 vanishedUser : vanishedUsers) { user.getPlayer().hidePlayer(vanishedUser.getPlayer()); }
		}
	}
	
	public void hideVanished(Player player) {
		if(!plugin.getVanished().isEmpty()) {
			List<User53> vanishedUsers = plugin.getVanished();
			for(User53 vanishedUser : vanishedUsers) { player.getPlayer().hidePlayer(vanishedUser.getPlayer()); }
		}
	}
	
	
}