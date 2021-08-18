package fr.pepintrie.pepintrieplugin.gods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public abstract class God {
	
	protected static String[] types = {"nether"};
	
	protected List<Location> temples = new ArrayList<>();
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

	public static String getTypes() {
		String str = "";
		for(int i = 0; i < types.length; i++) {
			str += types[i];
			if(!((i+1)==types.length)) {
				str += "|";
			}
		}
		return str;
	}

	public abstract String getType();
	 

}
