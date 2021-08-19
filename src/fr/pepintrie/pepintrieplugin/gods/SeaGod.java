package fr.pepintrie.pepintrieplugin.gods;

public class SeaGod extends God{

	public SeaGod(String name) {
		setName(name);
		color = "§9";
	}
	
	@Override
	public String getColorName() {
		return color + name;
	}

	@Override
	public String getType() {
		return color + "sea";
	}

	

}
