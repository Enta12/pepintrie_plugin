package fr.pepintrie.pepintrieplugin.gods.objects;

import java.io.Serializable;

public class Quest implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String Description;
	private int reward;
	private int id;
	private boolean isAltarGoal;

	public Quest(int reward, String goalDescription, int id, boolean isAltarGoal) {
		this.Description = goalDescription;
		this.id = id;
		this.isAltarGoal = isAltarGoal;
	}
	
	

	public String getDescription() {
		return Description;
	}



	public int getId() {
		return id;
	}



	public int getReward() {
		return reward;
	}



	public boolean getIsAnAltarGoal() {
		return isAltarGoal;
	}
}
