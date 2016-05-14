package net.econcraft.vanish53.listeners;

import net.econcraft.vanish53.Main53;
import net.econcraft.vanish53.User53;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class InventoryEvents implements Listener{
	
	private Main53 plugin;
	
	public InventoryEvents(Main53 main) {
		this.plugin = main;
	}
	
	@EventHandler
	public void openContainer(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = this.plugin.grabUser(player);
			
			if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(user.config.getBool(user.config.silentChestsPath) && !user.getPlayer().isSneaking() && user.perms.invPerm) {
					Inventory inv = null;
					Block block = e.getClickedBlock();
					BlockState blockState = block.getState();
					
					switch(block.getType()) {
						case CHEST:
							Chest chest = (Chest) blockState;
							inv = this.plugin.getServer().createInventory(user.getPlayer(), chest.getInventory().getSize());
							inv.setContents(chest.getInventory().getContents());
							break;
						case ENDER_CHEST:
							break;
						case DISPENSER:
							break;
						case HOPPER:
							break;
						case DROPPER:
							break;
						case FURNACE:
							break;
						case BREWING_STAND:
							break;
						case BEACON:
							break;
						default:
							inv = null;
							break;
					}
					if(inv != null) {
						e.setCancelled(true);
						user.getPlayer().closeInventory();
						user.getPlayer().openInventory(inv);
						this.plugin.addChestUser(user);
						user.tell(ChatColor.GRAY + "Silently opening container. Contents disabled. " + ChatColor.AQUA + "Hold SHIFT and click to open normally.");
						return;
					}
				}
			}
		}
	}

	@EventHandler
	public void inventoryClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = this.plugin.grabUser(player);
			if(this.plugin.isUsingChest(user)) {
				this.plugin.delChestUser(user);
			}
		}
	}
	
	@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		if(e.getWhoClicked() instanceof Player) {
			//this.plugin.log.info("Is Player");
			Player player = (Player) e.getWhoClicked();
			if(this.plugin.isUser(player)) {
				//this.plugin.log.info("Is User");
				User53 user = this.plugin.grabUser(player);
				if(user.isVanished() && this.plugin.isUsingChest(user)) {
					//this.plugin.log.info("Is in List");
					user.tell(ChatColor.RED + "Silent chest inventory manipulation is disabled.");
					e.setCancelled(true);
				}
			}
		}
	}
}