package fr.pepintrie.pepintrieplugin.gods.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.pepintrie.pepintrieplugin.gods.God;
import fr.pepintrie.pepintrieplugin.gods.GodsType;

public class Altar implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int size;
	private UUID playerUUID;
	private String playerName;
	private LocationSerializable location;
	private List<ArrayList<Goal>> goals = new ArrayList<ArrayList<Goal>>();
	private ArrayList<Quest> quests = new ArrayList<>();
	private God god;
	
	public Altar(Player player, Location location, God god) {
		this.god = god;
		this.playerName = player.getName();
		this.playerUUID = player.getUniqueId();
		this.location = new LocationSerializable(location);
		size = 0;
		set10Goals(goals);
	}
	
	public God getGod() {
		return god;
	}

	public String getName() {
		return playerName;
	}
	
	public UUID getPlayerUUID() {
		return playerUUID;
	}
	
	public Location getLocation() {
		return location.toLocation();
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
	
	public void createNewQuest(Player player, int event){
		Random random = new Random();
		if(event == 0) {
			if(god.getType() == GodsType.ECONOMY) {
				god.addPower(size*(random.nextInt(19)+1));
			}
			else if(player.getInventory().addItem(god.createARelic()).size() == 0) player.sendMessage(god.getColorName() + " : &fVoici un cadeau pour toi mon fidèle serviteur.");
			else {
				player.sendMessage(god.getColorName() + "Je voulais te faire un cadeau mais tu n'avais pas de place ... donc je l'ai absorbé");
				god.addPower(size*(random.nextInt(9)+1));
			}
		}
		else if (event <10) { //quest for altar or just give a relic
			if(size != 10) {
				boolean noQuest = true;
				for(Goal goal : goals.get(size)) {
					if(!goal.getIsOk() && !goal.getIsAvailable()) {
						player.sendMessage(god.getColorName() +" : &fJe veux que tu améliore mon temple en " + goal.getDescription());
						goal.setIsAvailable();
						quests.add(new Quest(random.nextInt((size+1)*10),goal.getDescription(), quests.size(), true));
						noQuest = false;
						break;
					}
				}
				if (noQuest) {
					boolean allGoalAreDone = true;
					for(Goal goal : goals.get(size)) {
						if(!goal.getIsOk()) {
							player.sendMessage(god.getColorName() + " : &fAs tu construit tout ce que je t'avais demandé ? ");
							allGoalAreDone = false;
							break;
						}
					}
					if(allGoalAreDone) {
						size +=1;
						player.sendMessage(god.getColorName() + " : &fHum, tu as bien tout fait, grâce à toi je suis lus puissant ");
						
					}
				}
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

	public boolean finish(int id) {
		for(Quest quest : quests) {
			if(quest.getId() == id) {
				if(quest.getIsAnAltarGoal()) {
					for(Goal goal : goals.get(size)) {
						if(goal.getDescription().equals(quest.getDescription())) {
							goal.setIsOk();
						}
					}
				}
				quests.remove(quest);
				god.addPower(quest.getReward());
				return true;
			}
		}
		return false;
	}

	public String getPlayerName() {
		return playerName;
	}
		
}



