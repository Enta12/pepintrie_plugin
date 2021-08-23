package fr.pepintrie.pepintrieplugin.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.pepintrie.pepintrieplugin.Main;
import fr.pepintrie.pepintrieplugin.gods.objects.Altar;
import fr.pepintrie.pepintrieplugin.gods.objects.Quest;

public class AltarListeners implements Listener{
	
	
	private Main main;
	
	public AltarListeners(Main main) {
		this.main = main;
	}
	
	private Altar getAnAltar(Block blockClicked) {
		if(blockClicked.getType() == Material.NETHERITE_BLOCK || blockClicked.getType() == Material.PRISMARINE_BRICKS || blockClicked.getType() == Material.EMERALD_BLOCK) {
			return main.getGods().getAPossibleAltar(blockClicked.getLocation());
		}
		return null;
	}
	
	private boolean isPlayerAltar(Altar altar, UUID playerAltarUUID) {
		if(playerAltarUUID.compareTo(altar.getPlayerUUID())==0) return true;
		return false;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Bukkit.broadcastMessage("onInteract 0");
		if(event.getClickedBlock() != null && event.getAction() == Action.LEFT_CLICK_BLOCK) {
			Altar altar = getAnAltar(event.getClickedBlock());
			if(altar != null) {
				if(isPlayerAltar(altar, event.getPlayer().getUniqueId())) {
					for(Quest quest : altar.getQuests()) {
						Bukkit.broadcastMessage("onInteract 1");
						if(quest.getIsAnAltarGoal()) event.getPlayer().sendMessage(altar.getGod().getColorName() + ": §fJe veux que tu améliore mon temple en " + quest.getDescription());
						else event.getPlayer().sendMessage(altar.getGod().getColorName() + ":§f Je veux que tu " + quest.getDescription());
					}
					Bukkit.broadcastMessage("onInteract 2");
				}
				Bukkit.broadcastMessage("onInteract 3");
			}
		}
	}
}
