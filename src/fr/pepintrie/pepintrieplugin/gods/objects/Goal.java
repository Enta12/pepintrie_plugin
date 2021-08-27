package fr.pepintrie.pepintrieplugin.gods.objects;

import java.io.Serializable;

public class Goal implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Description;
	private boolean isAvailable;
	private boolean isOk;

	public Goal(String goalDescription) {
		Description = goalDescription;
		isAvailable = false;
		isOk = false;
	}
	
	public boolean getIsOk() {
		return isOk;
	}
	
	public String getDescription() {
		return Description;
	}

	public boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsOk() {
		isOk = true;
		
	}

	public void setIsAvailable() {
		isAvailable = true;
		
	}
}
