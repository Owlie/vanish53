package net.econcraft.vanish53;

public class Perm53 {

	///////////////////////////////////////
	// PRIVATE MEMBERS
	///////////////////////////////////////
	private User53 user;
	
	///////////////////////////////////////
	// PUBLIC MEMBERS
	///////////////////////////////////////
	public Boolean vanishPerm;
	public Boolean seePerm;
	public Boolean otherPerm;
	public Boolean invPerm;
	public Boolean enderPerm;
	public Boolean potionPerm;
	public Boolean searchPerm;
	
	///////////////////////////////////////
	// CONSTRUCTOR
	///////////////////////////////////////
	public Perm53(User53 user) {
		this.setPerms(user);
	}
	
	///////////////////////////////////////
	// PRIVATE METHODS
	///////////////////////////////////////
	private void setPerms(User53 user) {
		
		this.user = user;
		
		this.vanishPerm = this.user.getPlayer().hasPermission("vanish53.vanish");
		this.seePerm = this.user.getPlayer().hasPermission("vanish53.see");
		this.otherPerm = this.user.getPlayer().hasPermission("vanish53.other");
		this.invPerm = this.user.getPlayer().hasPermission("vanish53.inv");
		this.enderPerm = this.user.getPlayer().hasPermission("vanish53.ender");
		this.potionPerm = this.user.getPlayer().hasPermission("vanish53.potion");
		this.searchPerm = this.user.getPlayer().hasPermission("vanish53.searc");
		
	}
}