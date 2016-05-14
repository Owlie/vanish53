package net.econcraft.vanish53;

import net.econcraft.vanish53.effects.Effects53;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class User53 {
	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////
	
	private Player player;
	private Boolean vanished;
	private String nickName;
	private Main53 plugin;
	
	///////////////////////////////////////
	// PUBLIC MEMBERS
	///////////////////////////////////////
	public Perm53 perms;
	public Config53 config;
	public Effects53 effects;
	
	///////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////
	public User53(final Player player, Main53 main) {
		this.player = player;
		this.plugin = main;
		this.perms = new Perm53(this);
		this.config = new Config53(this, this.plugin);
		this.effects = new Effects53(this, this.plugin);
		this.vanished = false;
		this.plugin.addUser(this);
	}
	
	///////////////////////////////////////
	// PUBLIC METHODS
	///////////////////////////////////////
	public void launchEffects(String toggle) {
		this.effects.launchEffects(toggle);
	}
	
	public void launchEffects(String toggle, String method) {
		this.effects.launchEffects(toggle, method);
	}
	
	public void tell(String msg) {
		this.getPlayer().sendMessage(plugin.pluginPrefix + msg);
	}
	
	public void hideMe() {
		this.setVanished(true);
		this.plugin.getHandler().hideUser(this);
		//plugin.hooks.setVanished(false);
	}
	
	public void showMe() {
		this.setVanished(false);
		this.plugin.getHandler().showUser(this);
		//plugin.hooks.setVanished(false);
	}
	
	public void sayState() {
		if(this.isVanished()) {
			this.tell(ChatColor.LIGHT_PURPLE + "You are currently " + ChatColor.RED + "HIDDEN" + ChatColor.LIGHT_PURPLE + " from all players.");
		} else {
			this.tell(ChatColor.LIGHT_PURPLE + "You are currently " + ChatColor.GREEN + "VISIBLE" + ChatColor.LIGHT_PURPLE + " to all players.");
		}
	}
	
	///////////////////////////////////////
	// PRIVATE METHODS
	///////////////////////////////////////
	
	///////////////////////////////////////
	// GET/SET METHODS
	///////////////////////////////////////
	
	public String myName() { return this.player.getName(); }
	
	public String getNick() { return this.nickName; }
	public void setNick(String nick) { this.nickName = nick; }	
	
	public Boolean isVanished() { if(this.vanished) { return true; } else { return false; } }
	
	public void setVanished(Boolean set) { 
		this.vanished = set;
		if(set) { this.plugin.addVanished(this); } else { this.plugin.delVanished(this); }
	}
	
	public Player getPlayer() { return this.player; }
	
}
