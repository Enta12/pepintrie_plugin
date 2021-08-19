package fr.pepintrie.pepintrieplugin.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.pepintrie.pepintrieplugin.Main;
import fr.pepintrie.pepintrieplugin.gods.God;


public class GodsListerners implements Listener{
	
	private static Main main;
	
	public GodsListerners(Main main) {
		GodsListerners.main = main;
	}

	@EventHandler()
	public void onPlaceClick(BlockPlaceEvent event) {
		if(event.getBlock().getType() == Material.RED_CANDLE) {
			Location candleLocation = event.getBlock().getLocation();
			isATemple(Material.NETHERITE_BLOCK, candleLocation, "nether");
		}
		else if(event.getBlock().getType() == Material.BLUE_CANDLE) {
			Location candleLocation = event.getBlock().getLocation();
			isATemple(Material.PRISMARINE_BRICKS, candleLocation, "sea");
		}
	}
	
	private static void isATemple(Material godBlockMaterial, Location candleLocation, String type) {
		Location godBlockLocation = new Location(candleLocation.getWorld(), candleLocation.getX(), candleLocation.getY() -1, candleLocation.getZ());
		if(godBlockLocation.getBlock().getType() == godBlockMaterial) {
			Location signLocation = findSign(godBlockLocation);
			if(signLocation != null) {
				signInstructions(signLocation, type, godBlockLocation);
				signLocation.getBlock().setType(Material.AIR);switch(type) {
					case "nether" :
						signLocation.getWorld().spawnParticle(Particle.PORTAL, signLocation, 1500);
						break;
					case "sea":
						signLocation.getWorld().spawnParticle(Particle.GLOW_SQUID_INK, signLocation, 150);
						break;
					default:
						break;
				}
			}
		}
	}
	
	private static void signInstructions(Location signLocation, String type, Location godBlockLocation) {
		Sign sign  = (Sign)signLocation.getBlock().getState();
		if(sign.getLine(0).equalsIgnoreCase("[GODS]") && !(sign.getLine(1) == "") && !(sign.getLine(2) == "")) {
			God god = main.getGods().getAGod(sign.getLine(1));
			if(god != null) {
				main.getGods().getAGod(sign.getLine(1)).addAnAltar(godBlockLocation, sign.getLine(2));
			}
			else{
				main.getGods().createAGod(type, sign.getLine(1));
				main.getGods().getAGod(sign.getLine(1)).addAnAltar(godBlockLocation, sign.getLine(2));
			}
		}
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
		if(block.getType() == Material.PRISMARINE_BRICKS || block.getType() == Material.NETHERITE_BLOCK) {
			main.getGods().removeAPossibleAltar(block.getLocation());
		}
	}
}
