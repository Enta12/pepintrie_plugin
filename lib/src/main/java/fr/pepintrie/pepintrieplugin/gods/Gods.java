package fr.pepintrie.pepintrieplugin.gods;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import fr.pepintrie.pepintrieplugin.gods.objects.Altar;

public class Gods implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private HashMap<String, God> gods = new HashMap<>();
	
	public Gods(HashMap<String, God> gods) {
		this.gods = gods;
	}
	
	

	public HashMap<String, God> getGods() {
		return gods;
	}
	
	public boolean deleteAGod(String godName) {
		if(gods.containsKey(godName)) {
			Bukkit.broadcastMessage(gods.get(godName).getColorName() + "&f a fini son règne");
			gods.remove(godName);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean createAGod(GodsType type, String name) {
		if(!gods.containsKey(name)) {
			God god;
			if(type == GodsType.NETHER) {
				god = new NetherGod(name);
			}
			else if(type == GodsType.SEA) {
				god = new SeaGod(name);
			}
			else if(type == GodsType.ECONOMY) {
				god = new EconomyGod(name);
			}
			else if(type == GodsType.CLIFF) {
				god = new CliffGod(name);
			}
			else if(type == GodsType.CAVE) {
				god = new CaveGod(name);
			}
			else {
				return false;
			}
			gods.put(name, god);
			Bukkit.broadcastMessage(god.getColorName() + " &f commence un règne");
			
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
		if(gods.containsKey(oldName)) {
			if(!gods.containsKey(newName)) {
				gods.get(oldName).setName(newName);
				return true;
			}
			return false;
		}
		return false;
	}

	

	public String getListString() {
		String str = "Gods are :\n";
		for(Entry<String, God> entry : gods.entrySet()) {
			str += entry.getValue().getColorName() + " " + entry.getValue().getType() + " god\n";
		}
		return str;
	}



	public God getAGod(String name) {
		if(!gods.containsKey(name)) {
			return null;
		}
		else {
			return gods.get(name);
		}
	}
	
	public Altar getAPossibleAltar(Location possibleAltar) {
		for(Entry<String, God> entry : gods.entrySet()) {
			for(Entry<String, Altar> altarEntry : entry.getValue().getAltars().entrySet()) {
				if(altarEntry.getValue().getLocation().getBlockX() == possibleAltar.getBlockX() 
						&& altarEntry.getValue().getLocation().getBlockY() == possibleAltar.getBlockY() 
						&& altarEntry.getValue().getLocation().getBlockZ() == possibleAltar.getBlockZ() 
						&& altarEntry.getValue().getLocation().getWorld() == possibleAltar.getWorld()) {
					return altarEntry.getValue();
				}
			}
		}
		return null;
	}
	

	public void removeAPossibleAltar(Location possibleAltar) {
		for(Entry<String, God> entry : gods.entrySet()) {
			boolean remove = false;
			for(Entry<String, Altar> altarEntry : entry.getValue().getAltars().entrySet()) {
				if(altarEntry.getValue().getLocation().getBlockX() == possibleAltar.getBlockX() 
						&& altarEntry.getValue().getLocation().getBlockY() == possibleAltar.getBlockY() 
						&& altarEntry.getValue().getLocation().getBlockZ() == possibleAltar.getBlockZ() 
						&& altarEntry.getValue().getLocation().getWorld() == possibleAltar.getWorld()) {
					entry.getValue().getAltars().remove(altarEntry.getKey());
					if(entry.getValue().getAltars().size() == 0) {
						gods.remove(entry.getKey());
						remove= true;
						break;
					}
				}
			}
			if(remove) break;
		}
	}
	
	public Altar getAltarFromAltarAndGodName(String altarName, String godName) {
		if(getAGod(godName) != null) {
				if(gods.get(godName).getAltars().containsKey(altarName)) return gods.get(godName).getAltars().get(altarName);
		}
		return null;
	}
	
	public Altar getAltarFromAltarName(String altarName) {
		for(Entry<String, God> entry : gods.entrySet()) {
				if(entry.getValue().getAltars().containsKey(altarName)) {
					return entry.getValue().getAltars().get(altarName);
			}
		}
		return null;
	}
	
	public GodsType getPlayerGodType(UUID uuid) {
		for(Entry<String, God> entry : gods.entrySet()) {
			for(UUID believerUuid : entry.getValue().getBelieverUUID()) {
				if(believerUuid.compareTo(uuid) == 0) {
					return entry.getValue().getType();
				}
			}
		}
		return null;
	}


	public boolean playerHaveAGod(UUID uuid) {
		for(Entry<String, God> entry : gods.entrySet()) {
			for(UUID believerUuid : entry.getValue().getBelieverUUID()) {
				if(believerUuid.compareTo(uuid) == 0) {
					return true;
				}
			}
		}
		return false;
	}



	public GodsType stringTypeToGodsType(String stringType) {
		if (stringType.equalsIgnoreCase("nether")) {
			return GodsType.NETHER;
		}
		else if (stringType.equalsIgnoreCase("sea")) {
			return GodsType.SEA;
		}
		else if(stringType.equalsIgnoreCase("economy")) {
			return GodsType.ECONOMY;
		}
		return null;
	}



	public HashMap<String, Altar> getAltars() {
		HashMap<String, Altar> altars = new HashMap<>();
		for(Entry<String, God> entry : gods.entrySet()) {
			for(Entry<String, Altar> altarEntry : entry.getValue().getAltars().entrySet()) {
				altars.put(altarEntry.getKey(), altarEntry.getValue());
			}
		}
		return altars;
	}



	public God getPlayerGod( UUID uuid) {
		for(Entry<String, God> entry : gods.entrySet()) {
			for(UUID believerUuid : entry.getValue().getBelieverUUID()) {
				if(believerUuid.compareTo(uuid) == 0) {
					return entry.getValue();
				}
			}
		}
		return null;
	}	
}
