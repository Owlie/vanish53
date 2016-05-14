package net.econcraft.vanish53.listeners;

import net.econcraft.vanish53.Main53;
import net.econcraft.vanish53.User53;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class EntityEvents implements Listener{
	
	private Main53 plugin;
	
	public EntityEvents(Main53 main) {
		this.plugin = main;
	}
	
	@EventHandler
	public void onMobTarget(EntityTargetEvent e) {
		if(e.getTarget() instanceof Player) {
			Player player = (Player) e.getTarget();
			if(this.plugin.isUser(player)) {
				User53 user = this.plugin.grabUser(player);
				if(user.isVanished() && user.config.getBool(user.config.ignoreMobsPath)) {
						e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onEntityClick(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		if(this.plugin.isUser(player)) {
			User53 user = this.plugin.grabUser(player);
			if(user.isVanished() && user.perms.invPerm) {
				Inventory inv;
				
				Player target = (Player) e.getRightClicked();
				
				inv = this.plugin.getServer().createInventory(target, 9, "Equipped");
				inv.setContents(target.getInventory().getArmorContents());
				
				user.getPlayer().closeInventory();
				user.getPlayer().openInventory(inv);
			}
		}
	}
}