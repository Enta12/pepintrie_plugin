package fr.pepintrie.pepintrieplugin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.pepintrie.pepintrieplugin.command.AltarsCommands;
import fr.pepintrie.pepintrieplugin.command.GodsCommands;
import fr.pepintrie.pepintrieplugin.command.LicenceCommand;
import fr.pepintrie.pepintrieplugin.command.QuestsCommands;
import fr.pepintrie.pepintrieplugin.gods.God;
import fr.pepintrie.pepintrieplugin.gods.Gods;
import fr.pepintrie.pepintrieplugin.listeners.AltarListeners;
import fr.pepintrie.pepintrieplugin.listeners.GodsListeners;
import fr.pepintrie.pepintrieplugin.listeners.RelicListeners;


public class Main extends JavaPlugin{
	
	
	private static Gods gods;
	
	@Override
	public void onEnable() {
		System.out.println("Start of pepintrie_plugin");
		File godsFile = new File("plugins/pepintrie_plugin/gods");
		if(godsFile.exists()) {
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(godsFile)));
				
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			

		}
		else {
	        File pepintrie_directory = new File("plugins/pepintrie_plugin");
	        if(pepintrie_directory.exists()) {
	        	
	        }
	        else {
	        	System.out.println("pepintrie_directory doesn't exist");
	        	if (pepintrie_directory.mkdir()) {
		            System.out.println("creation of pepintrie_directory succesfull");
		        }
		        else {
		            System.out.println("pepintrie_directory cannot be created");
		        }
	        }
		}
		
		gods = new Gods(new HashMap<>());
		getCommand("licence").setExecutor(new LicenceCommand());
		getCommand("gods").setExecutor(new GodsCommands(this));
		getCommand("altars").setExecutor(new AltarsCommands(this));
		getCommand("quests").setExecutor(new QuestsCommands(this));
		getServer().getPluginManager().registerEvents(new GodsListeners(this), this);
		getServer().getPluginManager().registerEvents(new AltarListeners(this), this);
		getServer().getPluginManager().registerEvents(new RelicListeners(this), this);
		Random random = new Random();
		
	}
	
	@Override
	public void onDisable() {
		System.out.println("End of pepintrie_plugin");
		File godsFile = new File("plugins/pepintrie_plugin/test");
		if(!godsFile.exists()) {
			try {
				godsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				ObjectOutputStream oos;
				oos = new ObjectOutputStream(
		                new BufferedOutputStream(
		                  new FileOutputStream(
		                		  godsFile)));
				oos.writeObject(gods);
		        oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public Gods getGods(){
		return gods;
	}
	
	
}
