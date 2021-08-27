package fr.pepintrie.pepintrieplugin.gods.objects;

import java.io.Serializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationSerializable implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private double x;
	private double y;
	private double z;
	private String world;

	public LocationSerializable(World world, double x, double y, double z) {
		this.world = world.getName();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public LocationSerializable() {
	}
	
	
	public LocationSerializable(Location location) {
		world = location.getWorld().getName();
		x = location.getBlockX();
		y = location.getBlockY();
		z = location.getBlockZ();
	}

	public Location toLocation() {
		return new Location(Bukkit.getWorld(world), x, y, z);
	}

}
