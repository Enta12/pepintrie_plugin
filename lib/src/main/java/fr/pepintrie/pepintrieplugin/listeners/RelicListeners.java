package fr.pepintrie.pepintrieplugin.listeners;

import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.util.Vector;
import org.bukkit.World;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.pepintrie.pepintrieplugin.Main;
import fr.pepintrie.pepintrieplugin.gods.God;
import fr.pepintrie.pepintrieplugin.gods.GodsType;
import fr.pepintrie.pepintrieplugin.gods.objects.Altar;
import fr.pepintrie.pepintrieplugin.task.TeleleportNether;

public class RelicListeners implements Listener{
	
	//private static  destructibleBlock = []
	private Main main;
	
	public RelicListeners(Main main){
		this.main = main;
	}

	/*----MAIN LISTENER OF RELIC----*/
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(event.getItem() != null) {
			if (event.getItem().getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 255) {
				if(usePower(event.getItem(), GodsType.NETHER, event.getPlayer())){
					for(Entry<String, Altar> entry : main.getGods().getAltars().entrySet()){
						if(event.getItem().getItemMeta().getLore().get(1).endsWith(entry.getValue().getPlayerName())) {
							event.getPlayer().getWorld().spawnParticle(Particle.PORTAL, event.getPlayer().getLocation(), 1500);
							(new TeleleportNether(event.getPlayer(), entry.getValue())).runTaskTimer(main, 20, 20);
							break;
						}
					}
					if(checkIfBreak(event.getItem())) event.getPlayer().getInventory().remove(event.getItem());
				}
				
			} else if(event.getItem().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) == 255) {
				if(usePower(event.getItem(), GodsType.SEA, event.getPlayer())){
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
			else if (event.getItem().getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 255) {
				if(usePower(event.getItem(), GodsType.CLIFF, event.getPlayer())){
					doToLookingZone(event, "PLACE");
					if(checkIfBreak(event.getItem())) event.getPlayer().getInventory().remove(event.getItem());
				}
				
			}
			else if (event.getItem().getEnchantmentLevel(Enchantment.ARROW_INFINITE) == 255) {
				if(usePower(event.getItem(), GodsType.CAVE, event.getPlayer())){
					doToLookingZone(event, "BREAK");
					if(checkIfBreak(event.getItem())) event.getPlayer().getInventory().remove(event.getItem());
				}
				
			}
			event.getPlayer().updateInventory();
		} else if(event.getItem().getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) == 255) { //TODO
		}
	}
	
	/*----FUNCTIONS FOR CAVE AND CLIFF GODS----*/

	public void doToLookingZone(PlayerInteractEvent event,String action){
		Player player = event.getPlayer();
		ItemStack relicItem = event.getItem();
		int width = getWidth(relicItem);
		int lenght = getlenght(relicItem);
		int height = getHeight(relicItem);
		char direction = getDirection(player);
		Location playerLocation = player.getBedLocation();
		
		for(int lenght_iterator = 0; lenght_iterator < lenght+1; lenght_iterator++){
			for(int height_iterator = -height; height_iterator < height+1; height_iterator++){
				for(int width_iterator = -width ; width_iterator < width; height_iterator++){
					int[] coordonate = new int[3];
					switch(direction){
						case 'N':
							coordonate[0] = (int) playerLocation.getX() + lenght_iterator;
							coordonate[1] = (int) playerLocation.getY() + height_iterator;
							coordonate[2] = (int) playerLocation.getZ() -lenght_iterator - 1;
							break;
						case 'S':
							coordonate[0] = (int) playerLocation.getX() + lenght_iterator;
							coordonate[1] = (int) playerLocation.getY() + height_iterator;
							coordonate[2] = (int) playerLocation.getZ() + lenght_iterator + 1;
							break;
						case 'W':
							coordonate[0] = (int) playerLocation.getX() + lenght_iterator + 1;
							coordonate[1] = (int) playerLocation.getY() + height_iterator;
							coordonate[2] = (int) playerLocation.getZ() + lenght_iterator;
							break;
						default:
							coordonate[0] = (int) playerLocation.getX() + lenght_iterator + 1;
							coordonate[1] = (int) playerLocation.getY() + height_iterator;
							coordonate[2] = (int) playerLocation.getZ() + lenght_iterator;
					}
					if(placeOrBreak(player, action, coordonate)==4){
						player.sendMessage(main.getGods().getPlayerGod(player.getUniqueId()).getColorName() +
						 " : &fJe ne peux pas poser ceci, ce n'est pas un block");
					}
				}
			}
		}
		player.updateInventory();
	}

	private int placeOrBreak(Player player, String action, int[] blockLocation){
		if (action.equals("PLACE") && (player.getWorld().getBlockAt(blockLocation[0], blockLocation[1], blockLocation[2]).getType() == Material.AIR){
			ItemStack block = player.getInventory().getItemInOffHand(); 
			if(block.getType().isBlock()){
				player.getWorld().getBlockAt(blockLocation[0], blockLocation[1], blockLocation[2]).setType(block.getType());
				player.getInventory().getItemInOffHand().setAmount(player.getInventory().getItemInOffHand().getAmount()-1);
				player.updateInventory();
				return 3;
			} 
			return 4;
		}
		if(action.equals("BREAK") && player.getWorld().getBlockAt(blockLocation[0], blockLocation[1], blockLocation[2]).getPistonMoveReaction() == PistonMoveReaction.MOVE ||
		player.getWorld().getBlockAt(blockLocation[0], blockLocation[1], blockLocation[2]).getPistonMoveReaction() == PistonMoveReaction.BLOCK){
			player.getWorld().getBlockAt(blockLocation[0], blockLocation[1], blockLocation[2]).setType(Material.AIR);
			return 0;
		}
		return 2;
	}

	private char getDirection(Player player){
		Vector direction = player.getLocation().getDirection();
		if(Math.abs(direction.getX()) > Math.abs(direction.getZ())){
			if(direction.getX() > 0){
				return 'W';
			}
			else {
				return 'E';
			}
		}
		else {
			if(direction.getZ() > 0){
				return 'S';
			}
			else {
				return 'N';
			}
		}

	}

	public int getWidth(ItemStack relicItem ){
		relicItem.getEnchantmentLevel(Enchantment.MULTISHOT);
		return 0;
	}

	public int getlenght(ItemStack relicItem){
		relicItem.getEnchantmentLevel(Enchantment.PIERCING); 
		return 0;
	}

	public int getHeight(ItemStack relicItem){
		relicItem.getEnchantmentLevel(Enchantment.OXYGEN);
		return 0;
	}

	/*----GENERAL----*/

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
				char[] newUtilisationLeftSentence = new char[utilisationLeftSentence.length-1];
				newUtilisationLeftSentence[0] = '9';
				for (int i = 1; i < newUtilisationLeftSentence.length; i++) {
					newUtilisationLeftSentence[i]=utilisationLeftSentence[i+1];
				}
				utilisationLeftSentence = newUtilisationLeftSentence;
			}
			else {
				utilisationLeftSentence[1] -=1;
			}
		}
		else {
			utilisationLeftSentence[0] -=1;
		}
		if (utilisationLeft == 1) return true;
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
        	if(god != null) {
	        	int power = Integer.valueOf(m.group(1));
	        	if(!(god.getType() == type || god.getType() == GodsType.ECONOMY))power*=2;
	        	if(!(power < god.getPower())){
	        		god.addPower(power);
	        		return true;
	        	}
	        	player.sendMessage(god.getColorName() + " : &fJe ne suis pas assez puissant");
        	}
        }
        return false;
	}
	
}