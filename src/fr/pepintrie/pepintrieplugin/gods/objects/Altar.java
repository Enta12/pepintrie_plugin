package fr.pepintrie.pepintrieplugin.gods.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Altar {
	private int size;
	private UUID playerUUID;
	private String playerName;
	private Location location;
	private List<ArrayList<Goal>> goals = new ArrayList<ArrayList<Goal>>();
	private ArrayList<Quest> quests = new ArrayList<>();
	private String colorName;
	
	public Altar(Player player, Location location, String colorName) {
		this.colorName = colorName;
		this.playerName = player.getName();
		this.playerUUID = player.getUniqueId();
		this.location = location;
		size = 0;
		set10Goals(goals);
	}

	public String getName() {
		return playerName;
	}
	
	public UUID getPlayerUUID() {
		return playerUUID;
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
	
	public void setGoal(int goalNumber, Goal goal) {
		goals.get(goalNumber).add(goal);
	}
	
	public ArrayList<Goal> getGoals(){
		return goals.get(size);
	}
	
	public ArrayList<Quest> getQuests(){
		return quests;
	}
	
	
	
	private void set10Goals(List<ArrayList<Goal>> goals2){
		for(int i = 0; i < 10; i++) {
			goals2.add(new ArrayList<Goal>());
		}
	}
	
	public void createNewQuest(Player player) {
		Random random = new Random();
		int event = random.nextInt(100);
		if(event == 0) {
				//Spawn a priest if their is no one or just give a relic
		}
		else if (event <10) { //quest for altar or just give a relic
			if(size != 10) {
				boolean noQuest = true;
				for(Goal goal : goals.get(size)) {
					if(!goal.getIsOk() && !goal.getIsAvailable()) {
						player.sendMessage(colorName +" : §fJe veux que tu améliore mon temple en " + goal.getDescription());
						quests.add(new Quest(random.nextInt((size+1)*10),goal.getDescription(), quests.size()));
						noQuest = false;
						break;
					}
				}
				if (noQuest) player.sendMessage(colorName + " : §fAs tu construit tout ce que je t'avais demandé ? ");
			}
		}
		else if (event <50) {
			// find  an relic
		}
		else if(event <90) {
			// sacrify
		}
		else if(event<98) {
			//raid
		}
		else {
			//something really bad
		}
	}
		
}



