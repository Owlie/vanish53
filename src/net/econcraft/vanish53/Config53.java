package net.econcraft.vanish53;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config53 {
	
	private Main53 plugin;
	private User53 user;
	private FileConfiguration myConfig;
	private File myFile;
	
	public Config53(User53 user, Main53 main) {
		this.plugin = main;
		this.user = user;
		this.myFile = new File(plugin.getDataFolder() + "/user", this.user.myName() + ".yml");
		if(!this.configExists()) { this.loadConfig(); }
	}
	
	public Boolean getBool(String path) {
		if(this.myConfig.getBoolean(path)) { return true; } else { return false; }
	}
	
	public String getString(String path) {
		return this.myConfig.getString(path);
	}
	
	///////////////////////////////////////
	// USER CONFIG
	///////////////////////////////////////
	private boolean configExists() {
		if(this.myConfig != null) { return true; } else { return false; }
	}
	
	public void loadConfig() {
		// Does the filePath exists in this.myFile?
		if(this.myFile.exists()) {
			this.myConfig = new YamlConfiguration();
			try {
				this.myConfig.load(myFile);
				this.plugin.log.info("[V53] Configuration file successfully LOADED for " + this.user.myName() + "!");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		} else {
			this.buildConfig();
		}
	}
	
	private void buildConfig() {
		this.myConfig = this.plugin.getConfig();
		if(this.saveMyConfig(this.myFile, this.myConfig)) {
			this.plugin.log.info("[V53] Configuration file successfully CREATED for " + this.user.myName() + "!");
		}
	}
	
	private boolean saveMyConfig(File newConfig, FileConfiguration myConfig) {
		try {
			myConfig.save(newConfig);
			return true;
		} catch (IOException e) {
			this.plugin.log.warning("Vanish53 could not SAVE a configuration file for user: " + this.user.myName() + "!");
			this.plugin.log.warning(e.getMessage());
			return false;
		}
	}
	
	///////////////////////////////////////
	// CONFIGURATION PATHS
	///////////////////////////////////////
	public String defaultvanishPath = "settings.default-vanish";
	public String silentJoinPath = "settings.silent-join";
	
	public String silentChestsPath = "settings.silent-chests";
	public String disableChatPath = "settings.disable-chat";
	public String ignoreMobsPath = "settings.ignore-mobs";
	public String ignoreItemsPath = "settings.ignore-items";
	public String disableDropPath = "settings.disable-drop";
	public String disableBuildPath = "settings.disable-build";
	public String disableTriggersPath = "settings.disable-triggers";
	public String disableDoorsPath = "settings.disable-doors";
	
	public String vanishEffectsPath = "vanish-effects.enabled";
	public String vanishBroadcastPath = "vanish-effects.broadcast";
	public String vanishMessagePath = "vanish-effects.message";
	public String vanishEffectLightning = "vanish-effects.lightning";
	public String vanishEffectGrowl = "vanish-effects.growl";
	public String vanishEffectSmoke = "vanish-effects.smoke";
	public String vanishEffectExplode = "vanish-effects.explosion";
	public String vanishEffectLevelup = "vanish-effects.levelup";
	public String vanishEffectDropExp = "vanish-effects.drop-exp";
	public String vanishEffectDropItem = "vanish-effects.drop-item";
	public String vanishEffectDropItemType = "vanish-effects.drop-item-type";
	
	public String unvanishEffectsPath = "unvanish-effects.enabled";
	public String unvanishBroadcastPath = "unvanish-effects.broadcast";
	public String unvanishMessagePath = "unvanish-effects.message";
	public String unvanishEffectLightning = "unvanish-effects.lightning";
	public String unvanishEffectGrowl = "unvanish-effects.growl";
	public String unvanishEffectSmoke = "unvanish-effects.smoke";
	public String unvanishEffectExplode = "unvanish-effects.explosion";
	public String unvanishEffectLevelup = "unvanish-effects.levelup";
	public String unvanishEffectDropExp = "unvanish-effects.drop-exp";
	public String unvanishEffectDropItem = "unvanish-effects.drop-item";
	public String unvanishEffectDropItemType = "unvanish-effects.drop-item-type";
	
}