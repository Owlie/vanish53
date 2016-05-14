package net.econcraft.vanish53.listeners;

import net.econcraft.vanish53.Main53;
import net.econcraft.vanish53.User53;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class ItemEvents implements Listener{
	private Main53 plugin;
	
	public ItemEvents(Main53 main) {
		this.plugin = main;
	}
	
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent e) {
		Player player = (Player) e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = plugin.grabUser(player);
			if(user.isVanished() && user.config.getBool(user.config.ignoreItemsPath)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Player player = (Player) e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = plugin.grabUser(player);
			if(user.isVanished() && user.config.getBool(user.config.disableDropPath)) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = this.plugin.grabUser(player);
			if(user.isVanished() && user.config.getBool(user.config.disableBuildPath) && !user.getPlayer().isSneaking()) {
				user.tell(ChatColor.RED + "You are trying to break a block while vanished!");
				e.setCancelled(true);
			}
		}
	}
}