package net.econcraft.vanish53.effects;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import net.econcraft.vanish53.Main53;
import net.econcraft.vanish53.User53;

public class Effects53 {

	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////
	private Main53 plugin;
	private User53 user;
	
	///////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////
	public Effects53(User53 user, Main53 main) {
		this.plugin = main;
		this.user = user;
		this.loadEffects();
	}
	
	public void launchEffects(String toggle) {
		if((toggle.equalsIgnoreCase("on") && this.loudVanishEnabled) || (toggle.equalsIgnoreCase("off") && this.loudUnvanishEnabled)) {
			this.fireEffect(toggle);
		}
	}
	
	public void launchEffects(String toggle, String effect) {
		if(toggle.equalsIgnoreCase("on") && this.loudVanishEnabled) {
			this.spawnEntity(effect);
		}
	}
	
	///////////////////////////////////////
	// EFFECTS
	///////////////////////////////////////
	public Boolean loudVanishEnabled;
	public Boolean loudVanishBroadcast;
	public Boolean lightningOn;
	public Boolean growlOn;
	public Boolean smokeOn;
	public Boolean explosionOn;
	public Boolean levelupOn;
	public Boolean dropexpOn;
	public Boolean dropitemOn;
	public String dropitemtypeOn;
	
	public Boolean loudUnvanishEnabled;
	public Boolean loudUnvanishBroadcast;
	public Boolean lightningOff;
	public Boolean growlOff;
	public Boolean smokeOff;
	public Boolean explosionOff;
	public Boolean levelupOff;
	public Boolean dropexpOff;
	public Boolean dropitemOff;
	public String dropitemtypeOff;
	
	private void loadEffects() {		
		this.loudVanishEnabled = this.user.config.getBool(this.user.config.vanishEffectsPath);
		this.loudVanishBroadcast = this.user.config.getBool(this.user.config.vanishBroadcastPath);
		this.lightningOn = this.user.config.getBool(this.user.config.vanishEffectLightning);
		this.growlOn = this.user.config.getBool(this.user.config.vanishEffectGrowl);
		this.smokeOn = this.user.config.getBool(this.user.config.vanishEffectSmoke);
		this.explosionOn = this.user.config.getBool(this.user.config.vanishEffectExplode);
		this.levelupOn = this.user.config.getBool(this.user.config.vanishEffectLevelup);
		this.dropexpOn = this.user.config.getBool(this.user.config.vanishEffectDropExp);
		this.dropitemOn = this.user.config.getBool(this.user.config.vanishEffectDropItem);
		this.dropitemtypeOn = this.user.config.getString(this.user.config.vanishEffectDropItemType);
		
		this.loudUnvanishEnabled = this.user.config.getBool(this.user.config.unvanishEffectsPath);
		this.loudUnvanishBroadcast = this.user.config.getBool(this.user.config.unvanishBroadcastPath);
		this.lightningOff = this.user.config.getBool(this.user.config.unvanishEffectLightning);
		this.growlOff = this.user.config.getBool(this.user.config.unvanishEffectGrowl);
		this.smokeOff = this.user.config.getBool(this.user.config.unvanishEffectSmoke);
		this.explosionOff = this.user.config.getBool(this.user.config.unvanishEffectExplode);
		this.levelupOff = this.user.config.getBool(this.user.config.unvanishEffectLevelup);
		this.dropexpOff = this.user.config.getBool(this.user.config.unvanishEffectDropExp);
		this.dropitemOff = this.user.config.getBool(this.user.config.unvanishEffectDropItem);
		this.dropitemtypeOff = this.user.config.getString(this.user.config.unvanishEffectDropItemType);
	}
	
	private void fireEffect(String toggle) {
		if(toggle.equalsIgnoreCase("on")) {
			if(this.lightningOn) { this.user.getPlayer().getWorld().strikeLightningEffect(this.user.getPlayer().getLocation()); }
			if(this.growlOn) { this.launchSound("mob.enderdragon.growl"); }
			if(this.smokeOn) { this.user.getPlayer().getWorld().playEffect(this.user.getPlayer().getLocation(), Effect.SMOKE, 10, 50); }
			double expX = this.user.getPlayer().getLocation().getX();
			double expY = this.user.getPlayer().getLocation().getY();
			double expZ = this.user.getPlayer().getLocation().getZ();
			if(this.explosionOn) { this.user.getPlayer().getWorld().createExplosion(expX,  expY, expZ, 10, false, false); }
			if(this.levelupOn) { this.launchSound("random.levelup"); }
			if(this.dropexpOn) { this.user.getPlayer().getWorld().spawnEntity(this.user.getPlayer().getLocation(), EntityType.EXPERIENCE_ORB); }
			//if(this.dropitemOn) { this.user.getPlayer().getWorld().dropItem(this.user.getPlayer().getLocation(), null); }
			
		} else if(toggle.equalsIgnoreCase("off")) {
			if(this.lightningOff) { this.user.getPlayer().getWorld().strikeLightningEffect(this.user.getPlayer().getLocation()); }
			if(this.growlOff) { this.launchSound("mob.enderdragon.growl"); }
			if(this.smokeOff) { this.user.getPlayer().getWorld().playEffect(this.user.getPlayer().getLocation(), Effect.SMOKE, 10, 50); }
			double expX = this.user.getPlayer().getLocation().getX();
			double expY = this.user.getPlayer().getLocation().getY();
			double expZ = this.user.getPlayer().getLocation().getZ();
			if(this.explosionOff) { this.user.getPlayer().getWorld().createExplosion(expX,  expY, expZ, 10, false, false); }
			if(this.levelupOff) { this.launchSound("random.levelup"); }
			if(this.dropexpOff) { this.user.getPlayer().getWorld().spawnEntity(this.user.getPlayer().getLocation(), EntityType.EXPERIENCE_ORB); }
			//if(this.dropitemOff) { this.user.getPlayer().getWorld().dropItem(this.user.getPlayer().getLocation(), null); }
		}
	}
	
	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////
	private void spawnEntity(String creature) {
		creature = creature.toLowerCase();
		Location spawnLoc = user.getPlayer().getLocation();
		switch(creature) {
			case("dragon"):
				spawnLoc.setY(spawnLoc.getY() + 2);
				this.user.getPlayer().getWorld().spawnEntity(spawnLoc, EntityType.ENDER_DRAGON);
				this.user.getPlayer().getWorld().strikeLightningEffect(user.getPlayer().getLocation());
				this.launchSound("mob.enderdragon.growl");
				break;
			case("chicken"):
				this.user.getPlayer().getWorld().spawnEntity(spawnLoc, EntityType.CHICKEN);
			this.launchSound("mob.chicken.plop");
				break;
			default:
				this.user.tell(ChatColor.RED + "This creature does not exists!");
				break;
		}
	}
	
	private void launchSound(String sound) {
		for(Player player : this.plugin.getServer().getOnlinePlayers()) {
			this.plugin.getServer().dispatchCommand(this.plugin.getServer().getConsoleSender(), "playsound " + sound + " " + player.getName().toString());
		}
	}
}