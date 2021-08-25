package fr.pepintrie.pepintrieplugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.pepintrie.pepintrieplugin.Main;
import fr.pepintrie.pepintrieplugin.gods.God;
import fr.pepintrie.pepintrieplugin.gods.GodsType;


public class GodsListeners implements Listener{
	
	private static Main main;
	
	public GodsListeners(Main main) {
		GodsListeners.main = main;
	}

	@EventHandler()
	public void onPlaceClick(BlockPlaceEvent event) {
		if(event.getBlock().getType() == Material.RED_CANDLE) {
			Location candleLocation = event.getBlock().getLocation();
			isATemple(Material.NETHERITE_BLOCK, candleLocation, GodsType.NETHER, event.getPlayer());
		}
		else if(event.getBlock().getType() == Material.BLUE_CANDLE) {
			Location candleLocation = event.getBlock().getLocation();
			isATemple(Material.PRISMARINE_BRICKS, candleLocation, GodsType.SEA, event.getPlayer());
		}
		else if(event.getBlock().getType() == Material.LIME_CANDLE) {
			Location candleLocation = event.getBlock().getLocation();
			isATemple(Material.EMERALD_BLOCK, candleLocation, GodsType.ECONOMY, event.getPlayer());
		}
		
	}
	
	private static void isATemple(Material godBlockMaterial, Location candleLocation, GodsType type, Player player) {
		Location godBlockLocation = new Location(candleLocation.getWorld(), candleLocation.getX(), candleLocation.getY() -1, candleLocation.getZ());
		if(godBlockLocation.getBlock().getType() == godBlockMaterial) {
			Location signLocation = findSign(godBlockLocation);
			if(signLocation != null) {
				if(type == GodsType.NETHER) {
					signLocation.getWorld().spawnParticle(Particle.PORTAL, signLocation, 1500);
					if(!signInstructions(signLocation, type, godBlockLocation, player)){
						signLocation.getWorld().spawnEntity(signLocation, EntityType.WITHER_SKELETON);
					}
				}
				else if(type == GodsType.SEA) {
					signLocation.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, signLocation, 150);
					if(!signInstructions(signLocation, type, godBlockLocation, player)) {
						signLocation.getWorld().spawnEntity(signLocation, EntityType.GUARDIAN);
					}
				}
				else if(type == GodsType.ECONOMY) {
					signLocation.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, signLocation, 150);
					if(!signInstructions(signLocation, type, godBlockLocation, player)) {
						signLocation.getWorld().spawnEntity(signLocation, EntityType.PILLAGER);
					}
					
				}
				signLocation.getBlock().setType(Material.AIR);
			}
		}
	}
	
	private static boolean signInstructions(Location signLocation, GodsType type, Location godBlockLocation, Player player) {
		Sign sign  = (Sign)signLocation.getBlock().getState();
		if(sign.getLine(0).equalsIgnoreCase("[GODS]") && !(sign.getLine(1) == "")) {
			God god = main.getGods().getAGod(sign.getLine(1));
			if(main.getGods().playerHaveAGod(player.getUniqueId())) {
				return false;
			}
			else { 
				if (god != null) {
					main.getGods().getAGod(sign.getLine(1)).addAnAltar(godBlockLocation, player);
					main.getGods().getAGod(sign.getLine(1)).addABeliever(player.getUniqueId());
					return true;
				}
				else {
					main.getGods().createAGod(type, sign.getLine(1));
					main.getGods().getAGod(sign.getLine(1)).addAnAltar(godBlockLocation, player);
					main.getGods().getAGod(sign.getLine(1)).addABeliever(player.getUniqueId());
					return true;
				}
			}
		}
		return false;
	}
	
	private static Location findSign(Location godBlock) {
		Location signLocation = new Location(godBlock.getWorld(), godBlock.getX()-1, godBlock.getY(), godBlock.getZ());
		if(signLocation.getBlock().getState() instanceof Sign) { //X-1
			return signLocation;
		}
		else {
			signLocation.setX(godBlock.getX()+1);
			if(signLocation.getBlock().getState() instanceof Sign) { //X+1
				return signLocation;
			}
			else {
				signLocation.setX(godBlock.getX());
				signLocation.setZ(godBlock.getZ()-1);
				if(signLocation.getBlock().getState() instanceof Sign) { //Z-1
					return signLocation;
				}
				else {
					signLocation.setZ(godBlock.getZ()+1);
					if(signLocation.getBlock().getState() instanceof Sign) { //Z+1
						return signLocation;
					}
					else {
						return null;
					}
				}
			}
		}
	}
	
	@EventHandler()
	public void onBreakBlock(BlockBreakEvent event) {
		Block block = event.getBlock();
		if(block.getType() == Material.PRISMARINE_BRICKS || block.getType() == Material.NETHERITE_BLOCK || block.getType() == Material.EMERALD_BLOCK) {
			main.getGods().removeAPossibleAltar(block.getLocation());
		}
	}
}
