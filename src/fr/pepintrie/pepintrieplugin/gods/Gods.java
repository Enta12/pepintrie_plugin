package fr.pepintrie.pepintrieplugin.gods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import fr.pepintrie.pepintrieplugin.gods.objects.Altar;

public class Gods {
	private ArrayList<God> gods = new ArrayList<>();
	
	public Gods(ArrayList<God> gods) {
		this.gods = gods;
	}
	
	

	public List<God> getGods() {
		return gods;
	}
	
	public boolean deleteAGod(String godName) {
		final int id = getGodId(godName);
		if(id != -1) {
			gods.remove(id);
			return true;
		}
		return false;
	}
	
	private int getGodId(String godName) {
		for(int i = 0; i < gods.size(); i++) {
			if(gods.get(i).getName().equalsIgnoreCase(godName)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean isAType(String type) {
		String[] types = God.getTypes();
		for(int i = 0 ; i< types.length; i++) {
			if(type.equalsIgnoreCase(types[i])) return true;
		}
		return false;
	}
	
	public boolean createAGod(String type, String name) {
		if(getGodId(name) == -1) {
			switch(type) {
			case "nether":
				gods.add(new NetherGod(name));
				return true;
			case "sea":
				gods.add(new SeaGod(name));
				return true;
			default:
				return false;
			}
		}
		return false;
	}



	public String getTypesString() {
		String str = "";
		String[] types = God.getTypes();
		for(int i = 0 ; i< types.length; i++) {
			str += types[i];
			if(i+1<types.length) str += " | ";
		}
		return str;
	}
	
	public boolean renameAGod(String oldName, String newName) {
		int id = getGodId(oldName);
		if(id != -1) {
			if(getGodId(newName) == -1) {
				gods.get(id).setName(newName);
				return true;
			}
			return false;
		}
		return false;
	}

	

	public String getListString() {
		String str = "Gods are :\n";
		for(God god : gods) {
			str += god.getColorName() + " " + god.getType() + " god\n";
		}
		return str;
	}



	public God getAGod(String name) {
		int id = getGodId(name);
		if(id == -1) {
			return null;
		}
		else return gods.get(getGodId(name));
	}
	

	public void removeAPossibleAltar(Location possibleAltar) {
		for(God god : gods) {
			for(Altar altar : god.getAltars()) {
				if(altar.getLocation().getBlockX() == possibleAltar.getBlockX() && altar.getLocation().getBlockY() == possibleAltar.getBlockY() && altar.getLocation().getBlockZ() == possibleAltar.getBlockZ() && altar.getLocation().getWorld() == possibleAltar.getWorld()) {
					god.getAltars().remove(altar);
					if(god.getAltars().size() == 0) {
						Bukkit.broadcastMessage("Le dernier temple de " + god.getColorName() + "§f est tombé, et avec lui " +  god.getColorName());
						gods.remove(god);
					}
				}
			}
		}
	}
	
	public Altar getAltarFromAltarAndGodName(String altarName, String godName) {
		if(getAGod(godName) != null) {
				for(Altar altar :getAGod(godName).getAltars()) {
					if(altar.getName().equalsIgnoreCase(altarName)) {
						return altar;
				}
			}
		}
		return null;
	}
	
	
}
