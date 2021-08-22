package fr.pepintrie.pepintrieplugin.gods.objects;

public class Goal{
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
}
