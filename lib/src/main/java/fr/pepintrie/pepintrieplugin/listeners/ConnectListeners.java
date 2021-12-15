package fr.pepintrie.pepintrieplugin.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class ConnectListeners implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		God god = main.getGods().getPlayerGod(event.getPlayer().getUniqueId)
		if (god != null)
			god.spawnParticule(event.getPlayer().getLocation);

	}

	@EventHandler
	public void onDisconet(PlayerQuitEvent event) {
		God god = 
		if (god != null)
			god.spawnParticule(event.getPlayer().getLocation);
		
	}
}