package net.econcraft.vanish53.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.econcraft.vanish53.Main53;
import net.econcraft.vanish53.User53;

public class PlayerEvents implements Listener {
	
	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////	
	private Main53 plugin;
	
	///////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////
	public PlayerEvents(Main53 main) {
		this.plugin = main;
	}
	
	///////////////////////////////////////
	// PLAYER CHAT
	///////////////////////////////////////
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = (Player) e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = plugin.grabUser(player);
			if(user.isVanished() && user.config.getBool(user.config.disableChatPath)) {
				user.tell(ChatColor.RED + "You are trying to chat while vanished!");
				e.setCancelled(true);
			}
		}
	}
	
	///////////////////////////////////////
	// PLAYER JOIN
	///////////////////////////////////////
	@EventHandler
	public void onEarlyJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		this.plugin.log.info("[V53] Player " + player.getName() + " joined, and is now a V53 User.");
		if(player.hasPermission("vanish53.vanish")) {
			
			// This player is now considered a USER and will be treated as such
			User53 user = this.plugin.grabUser(player);
			
			// Check to see if the user is already loaded. If so, reload it. If not, create a new user.
			if(user == null) { 
				user = new User53(player, this.plugin); 
			} else {
				user.config.loadConfig();
			}
			
			if(user.config.getBool(user.config.silentJoinPath)) {
				this.plugin.getHandler().vanishUser(user, "on", "s");
				e.setJoinMessage("");
				return;
			} else {
			}
			
		} else {
			this.plugin.getHandler().hideVanished(player);
		}
	}
	
	///////////////////////////////////////
	// PLAYER QUIT
	///////////////////////////////////////
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = this.plugin.grabUser(player);
			if(user != null) {
				if(user.isVanished()) {
					e.setQuitMessage("");
					this.plugin.delVanished(user);
				}
				this.plugin.delUser(user);
			}
		}
	}
	
	///////////////////////////////////////
	// PLAYER INTERACT
	///////////////////////////////////////
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = this.plugin.grabUser(player);
			if(user.isVanished()) {
				////////////////////////////////////////////////////////////////
				// DISABLE INTERACTION WITH NORMAL PRESSURE PLATES AND TRIPWIRES
				////////////////////////////////////////////////////////////////
				if(e.getAction().equals(Action.PHYSICAL)) {
					if(user.config.getBool(user.config.disableTriggersPath)) {
						if(
							e.getClickedBlock().getType() == Material.STONE_PLATE || 
							e.getClickedBlock().getType() == Material.WOOD_PLATE ||
							e.getClickedBlock().getType() == Material.TRIPWIRE ||
							e.getClickedBlock().getType() == Material.TRIPWIRE_HOOK
								
						){
							e.setCancelled(true);
							return;
						}
					}
				}
				////////////////////////////////////////////////////////////////
				// DISABLE INTERACTION WITH LEVERS AND BUTTONS
				////////////////////////////////////////////////////////////////
				if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if(user.config.getBool(user.config.disableTriggersPath) && !user.getPlayer().isSneaking()) {
						if(
							e.getClickedBlock().getType().equals(Material.LEVER) ||
							e.getClickedBlock().getType().equals(Material.STONE_BUTTON) ||
							e.getClickedBlock().getType().equals(Material.WOOD_BUTTON)
						){
							user.tell(ChatColor.GRAY + "Triggers disabled while vanished!");
							e.setCancelled(true);
							return;
						}
					}
				}
				////////////////////////////////////////////////////////////////
				// DISABLE DOORS, GATES, AND TRAPDOORS
				////////////////////////////////////////////////////////////////
				if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					if(user.config.getBool(user.config.disableDoorsPath) && !user.getPlayer().isSneaking()) {
						if(
							e.getClickedBlock().getType().equals(Material.WOOD_DOOR) ||
							e.getClickedBlock().getType().equals(Material.TRAP_DOOR) ||
							e.getClickedBlock().getType().equals(Material.FENCE_GATE) ||
							e.getClickedBlock().getType().equals(Material.WOODEN_DOOR)
						) {
							user.tell(ChatColor.GRAY + "Doors disabled while vanished!");
							e.setCancelled(true);
							return;
						}
					}
				}
				
			}
		}
	}
}