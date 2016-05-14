package net.econcraft.vanish53;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.econcraft.vanish53.listeners.*;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main53 extends JavaPlugin {
	///////////////////////////////////////
	// PUBLIC MEMBERS
	///////////////////////////////////////
	public Logger log = Logger.getLogger("Minecraft");
	public String pluginPrefix = (ChatColor.RED + "[" + ChatColor.GREEN + "V" + ChatColor.AQUA + "53" + ChatColor.RED + "] ");
	
	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////
	private Handler53 handler;
	private List<User53> vUsers;
	private List<User53> usersOnline;
	private List<User53> chestUsers;
	
	///////////////////////////////////////
	// ENABLE/DISABLE PLUGIN
	///////////////////////////////////////
	public void onEnable() {
		this.handler = new Handler53(this);
		this.saveDefaultConfig();
		vUsers = new ArrayList<User53>();
		usersOnline = new ArrayList<User53>();
		chestUsers = new ArrayList<User53>();
		
		// REGISTER COMMAND EXECUTOR
		this.getCommand("vanish").setExecutor(new Command53(this));
		
		// REGISTER LISTENERS
		this.getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new EntityEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new ItemEvents(this), this);
		this.getServer().getPluginManager().registerEvents(new InventoryEvents(this), this);
	}
	
	public void onDisable() {
		this.vUsers.clear();
		this.usersOnline.clear();
	}
	
	///////////////////////////////////////
	// PUBLIC METHODS
	///////////////////////////////////////
	
	///////////////////////////////////////
	// PRIVATE METHODS
	///////////////////////////////////////
	
	///////////////////////////////////////
	// GET/SET METHODS
	///////////////////////////////////////
	
	public Handler53 getHandler() { return this.handler; }
	
	// VANISHED
	public void addVanished(User53 user) { 
		this.vUsers.add(user);
	}
	public void delVanished(User53 user) {this.vUsers.remove(user); }
	public List<User53> getVanished() { return this.vUsers; }
	
	// USER
	public void addUser(User53 user) { this.usersOnline.add(user); }
	public void delUser(User53 user) { this.usersOnline.remove(user); }
	public User53 grabUser(Player base) {
		if(!this.usersOnline.isEmpty()) {
			for(User53 user : usersOnline) {
				if(user.myName().equalsIgnoreCase(base.getName())) {
					return user;
				}
			}
		}
		return null;
	}
	public Boolean isUser(Player base) {
		if(!this.usersOnline.isEmpty()) {
			for(User53 user : usersOnline) {
				if(user.myName().equalsIgnoreCase(base.getName())) {
					return true;
				}
			}
		}
		return false;
	}
	
	// CHEST USERS
	public void addChestUser(User53 user) { this.chestUsers.add(user); /*this.log.info("Added chest user");*/ }
	public void delChestUser(User53 user) { this.chestUsers.remove(user); /*this.log.info("Removed chest user");*/ }
	//public List<User53> getChestUsers() { return this.chestUsers; }
	public Boolean isUsingChest(User53 user) { 
		if(this.chestUsers.contains(user)) { 
			return true; 
		} else { 
			return false; 
		}
	}
}