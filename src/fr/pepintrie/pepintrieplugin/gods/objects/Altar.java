package fr.pepintrie.pepintrieplugin.gods.objects;

import org.bukkit.Location;

public class Altar {
	private int size;
	private String name;
	private Location location;
	
	public Altar(String name, Location location) {
		this.name = name;
		this.location = location;
		size = 0;
	}

	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size){
		this.size = size;
	}
}
