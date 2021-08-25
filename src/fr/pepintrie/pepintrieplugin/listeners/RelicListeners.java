package fr.pepintrie.pepintrieplugin.listeners;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.pepintrie.pepintrieplugin.Main;
import fr.pepintrie.pepintrieplugin.gods.God;
import fr.pepintrie.pepintrieplugin.gods.GodsType;
import fr.pepintrie.pepintrieplugin.gods.objects.Altar;
import fr.pepintrie.pepintrieplugin.gods.objects.Relic;
import task.TeleleportNether;

public class RelicListeners implements Listener{
	
	private Main main;
	
	public RelicListeners(Main main){
		this.main = main;
	}
	
	
	//for test
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.getInventory().clear();
		ItemStack test = Relic.getNetherRelic(15, "Enta12", "Enta12", "Enta12", 0);
		player.getInventory().addItem(test);
		player.updateInventory(); 
		String prout =  " ";
		prout.endsWith("Godname");
	}
	
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem() != null) {
			if (event.getItem().getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 255) {
				if(usePower(event.getItem(), GodsType.NETHER, event.getPlayer())){
					Bukkit.broadcastMessage("relic nether");
					for(Altar altar : main.getGods().getAltars()){
						if(event.getItem().getItemMeta().getLore().get(1).endsWith(altar.getPlayerName())) {
							event.getPlayer().getWorld().spawnParticle(Particle.PORTAL, event.getPlayer().getLocation(), 1500);
							(new TeleleportNether(event.getPlayer(), altar)).runTaskTimer(main, 20, 20);
							break;
						}
					}
					if(checkIfBreak(event.getItem())) event.getPlayer().getInventory().remove(event.getItem());
				}
				
			} else if(event.getItem().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) == 255) {
				if(usePower(event.getItem(), GodsType.SEA, event.getPlayer())){
					Bukkit.broadcastMessage("relic sea");
					event.getPlayer().setCompassTarget(new Location(Bukkit.getWorld("world"),event.getItem().getEnchantmentLevel(Enchantment.ARROW_DAMAGE)*10000 + event.getItem().getEnchantmentLevel(Enchantment.ARROW_FIRE)*100 + event.getItem().getEnchantmentLevel(Enchantment.LOYALTY),event.getItem().getEnchantmentLevel(Enchantment.FIRE_ASPECT),event.getItem().getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK)*10000 + event.getItem().getEnchantmentLevel(Enchantment.BINDING_CURSE)*100 + event.getItem().getEnchantmentLevel(Enchantment.CHANNELING)));
					Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
						@Override
						public void run() {
							event.getPlayer().setCompassTarget(Bukkit.getWorld("world").getSpawnLocation());
						}
					}, 400);
					if(checkIfBreak(event.getItem())) event.getPlayer().getInventory().remove(event.getItem());
				}
			}
			event.getPlayer().updateInventory();
		}
	}
	
	public boolean checkIfBreak(ItemStack relic) {
		List<String> lore = relic.getItemMeta().getLore();
		char[] utilisationLeftSentence = lore.get(lore.size()-1).toCharArray();
		int utilisationLeft;
		if(utilisationLeftSentence[1] == '0' || utilisationLeftSentence[1] == '1' ||utilisationLeftSentence[1] == '2' ||utilisationLeftSentence[1] == '3' ||utilisationLeftSentence[1] == '4' ||utilisationLeftSentence[1] == '5' ||utilisationLeftSentence[1] == '6' ||utilisationLeftSentence[1] == '7' ||utilisationLeftSentence[1] == '8' ||utilisationLeftSentence[1] == '9' ) {
			utilisationLeft = (utilisationLeftSentence[0]-48) * 10 + utilisationLeftSentence[1]-48;
		}
		else utilisationLeft = utilisationLeftSentence[0]-48;
		if(utilisationLeft>9) {
			if(utilisationLeft-1<10) {
				Bukkit.broadcastMessage("test1");
				char[] newUtilisationLeftSentence = new char[utilisationLeftSentence.length-1];
				newUtilisationLeftSentence[0] = '9';
				for (int i = 1; i < newUtilisationLeftSentence.length; i++) {
					newUtilisationLeftSentence[i]=utilisationLeftSentence[i+1];
				}
				utilisationLeftSentence = newUtilisationLeftSentence;
			}
			else {
				Bukkit.broadcastMessage("test2");
				utilisationLeftSentence[1] -=1;
			}
		}
		else {
			utilisationLeftSentence[0] -=1;
		}
		if (utilisationLeft == 0) return true;
		else {
			lore.set(lore.size()-1, new String(utilisationLeftSentence));
			ItemMeta meta = relic.getItemMeta();
			meta.setLore(lore);
			relic.setItemMeta(meta);

			return false;
		}
	}
	
	public boolean usePower(ItemStack relic, GodsType type, Player player) {
		Pattern p = Pattern.compile("L'utilisation necessite ([0-9]+)");
        Matcher m = p.matcher(relic.getItemMeta().getLore().get(2));
        if (m.find()) 
        {
        	God god = main.getGods().getPlayerGod(player.getUniqueId());
        	int power = Integer.valueOf(m.group(1));
        	if(!(god.getType() == type || god.getType() == GodsType.ECONOMY))power*=2;
        	if(!(power < god.getPower())){
        		god.addPower(power);
        		return true;
        	}
        	player.sendMessage(god.getColorName() + " : §fJe ne suis pas assez puissant");
        }
        return false;
	}
	
}