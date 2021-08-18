package fr.pepintrie.pepintrieplugin.gods;

public class NetherGod extends God{
	
	
	public NetherGod(String name) {
		setName(name);
		color = "§4";
	}

	@Override
	public String getColorName() {
		return "§4" + name;
	}

	@Override
	public String getType() {
		return "§4nether";
	}

}
