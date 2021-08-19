package fr.pepintrie.pepintrieplugin.gods;

public class NetherGod extends God{
	
	
	public NetherGod(String name) {
		setName(name);
		color = "�4";
	}

	@Override
	public String getColorName() {
		return color + name;
	}

	@Override
	public String getType() {
		return color + "nether";
	}

}
