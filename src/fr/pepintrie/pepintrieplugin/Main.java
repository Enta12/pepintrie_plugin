package fr.pepintrie.pepintrieplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pepintrie.pepintrieplugin.command.GodsCommands;
import fr.pepintrie.pepintrieplugin.command.LicenceCommand;
import fr.pepintrie.pepintrieplugin.gods.God;


public class Main extends JavaPlugin{
	
	private List<God> gods = new ArrayList<>();
	
	@Override
	public void onEnable() {
		System.out.println("Start of pepintrie_plugin");
		getCommand("licence").setExecutor(new LicenceCommand());
		getCommand("gods").setExecutor(new GodsCommands(this));
	}
	
	@Override
	public void onDisable() {
		System.out.println("End of pepintrie_plugin");
	}
	
	public List<God> getGods(){
		return gods;
	}
	
	
}
