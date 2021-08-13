package fr.pepintrie.pepintrie_plugin;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pepintrie.pepintrie_plugin.command.LicenceCommand;


public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		System.out.println("Start of pepintrie_plugin");
		getCommand("licence").setExecutor(new LicenceCommand());
	}
	
	@Override
	public void onDisable() {
		System.out.println("End of pepintrie_plugin");
	}
	
	
}
