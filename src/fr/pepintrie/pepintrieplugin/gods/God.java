package fr.pepintrie.pepintrieplugin.gods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class God {
	
	protected static String[] types = {"nether", "sea"};
	
	protected List<Location> altars = new ArrayList<>();
	protected List<Player> believer = new ArrayList<>();
	protected Player priest;
	//protected List<Relic> relics = new ArrayList<>();
	protected float power;
	protected String name;
	protected String color;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public abstract String getColorName();

	public static boolean isAType(String maybeType) {
		for(String type : types) {
			if(maybeType.equalsIgnoreCase(type)) return true;
		}
		return false;
	}

	public static String[] getTypes() {
		return types;
	}

	public abstract String getType();
	
	public void addAnAltar(Location altar) {
		if(altars.contains(altar))return;
		altars.add(altar);
	}

	public  List<Location> getAltars() {
		return altars;
	}
}
