package fr.pepintrie.pepintrieplugin.gods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.pepintrie.pepintrieplugin.gods.objects.Altar;

public abstract class God {
	
	protected static String[] types = {"nether", "sea"};
	
	protected List<Altar> altars = new ArrayList<>();
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
	
	public void addAnAltar(Location location, String name) {
		for(Altar altar : altars) {
			if(name.equalsIgnoreCase(altar.getName()))return;
		}
		altars.add(new Altar(name, location));
	}

	public  List<Altar> getAltars() {
		return altars;
	}
	

	public String altarsToString() {
		String str = "Altars of " + getColorName() + "§f are : ";
		for(int i = 0; i < altars.size(); i++) {
			str += altars.get(i).getName();
			if (i+1 < altars.size()) str += ", ";
		}
		return str;	
	}
}
