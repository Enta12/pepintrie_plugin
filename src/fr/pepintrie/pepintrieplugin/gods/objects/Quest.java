package fr.pepintrie.pepintrieplugin.gods.objects;

public class Quest {
	
	private String Description;
	private int reward;
	private int id;

	public Quest(int reward, String goalDescription, int id) {
		this.Description = goalDescription;
		this.id = id;
	}
	
	

	public String getDescription() {
		return Description;
	}
}
