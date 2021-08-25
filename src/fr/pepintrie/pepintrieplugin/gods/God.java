package fr.pepintrie.pepintrieplugin.gods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.pepintrie.pepintrieplugin.gods.objects.Altar;
import fr.pepintrie.pepintrieplugin.gods.objects.Goal;

public abstract class God {
	
	protected static String[] types = {"nether", "sea", "economy"};
	
	protected List<Altar> altars = new ArrayList<>();
	protected List<UUID> believerUUID = new ArrayList<>();
	protected Player priest;
	protected int power;
	protected String name;
	protected String color;
	protected GodsType type;
	protected List<ArrayList<Goal>> goals = new ArrayList<ArrayList<Goal>>();
	protected static Random random = new Random();
	
	public God(String name) {
		setName(name);
		getGoals();
	}
	
	public List<UUID> getBelieverUUID(){
		return believerUUID;
	}
	
	protected abstract void getGoals();
		
	protected void setGoals(Altar altar) {
		for(int i = 0; i<10;i++) {
			for(Goal goal : goals.get(i)) {
				altar.setGoal(i, goal);
			}
		}
	}
	
	protected void set10Goals(List<ArrayList<Goal>> goals2){
		for(int i = 0; i < 10; i++) {
			goals2.add(new ArrayList<Goal>());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getColorName() {
		return color + name;
	}

	public static boolean isAType(String maybeType) {
		for(String type : types) {
			if(maybeType.equalsIgnoreCase(type)) return true;
		}
		return false;
	}

	public static String[] getTypes() {
		return types;
	}

	public GodsType getType() {
		return type;
	}

	
	public void addAnAltar(Location location, Player player) {
		Altar altar = new Altar(player, location, this);
		altars.add(altar);
		setGoals(altar);
	}

	public  List<Altar> getAltars() {
		return altars;
	}
	

	public String altarsToString() {
		String str = "Altars of " + getColorName() + "�f are : ";
		for(int i = 0; i < altars.size(); i++) {
			str += altars.get(i).getName();
			if (i+1 < altars.size()) str += ", ";
		}
		return str;	
	}


	public void addABeliever(UUID uniqueId) {
		believerUUID.add(uniqueId);
		
	}

	public void addPower(int power) {
		this.power += power;
		
	}
	
	public abstract ItemStack createARelic();

	public int getPower() {
		return power;
	}
}
