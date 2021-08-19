package fr.pepintrie.pepintrieplugin;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pepintrie.pepintrieplugin.command.GodsCommands;
import fr.pepintrie.pepintrieplugin.command.LicenceCommand;
import fr.pepintrie.pepintrieplugin.gods.Gods;
import fr.pepintrie.pepintrieplugin.listeners.GodsListerners;


public class Main extends JavaPlugin{
	
	private Gods gods;
	
	@Override
	public void onEnable() {
		System.out.println("Start of pepintrie_plugin");
		gods = new Gods(new ArrayList<>());
		getCommand("licence").setExecutor(new LicenceCommand());
		getCommand("gods").setExecutor(new GodsCommands(this));
		getServer().getPluginManager().registerEvents(new GodsListerners(this), this);
		
		/*
		//import org.bukkit.configuration.file.YamlConfiguration;
		//import java.io.File;
		final File file = new File(this.getDataFolder(), "gods.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		if configurationSection = configuration.getConfigurationSection(key);
		*/
	}
	
	@Override
	public void onDisable() {
		System.out.println("End of pepintrie_plugin");
		
		/*
		//import org.bukkit.configuration.file.YamlConfiguration;
		//import java.io.File;
		final File file = new File(this.getDataFolder(), "gods.yml");
		final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
		configuration.set(gods.get(0).getName() + ".power" , gods.get(0).getPower());
		configuration.set(gods.get(0).getName() + ".prier", gods.get(0).getPriers());
		try{
			configuration.save(file);
		}catch (IOException e){
			e.printStackTrace();
		}
		*/
	}
	
	public Gods getGods(){
		return gods;
	}
	
	
}
