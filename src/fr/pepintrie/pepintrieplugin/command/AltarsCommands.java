package fr.pepintrie.pepintrieplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.pepintrie.pepintrieplugin.Main;

public class AltarsCommands implements CommandExecutor {
	
	private Main main;

	public AltarsCommands(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
		if(tag.equalsIgnoreCase("altars")) {
			if(sender instanceof Player) {
				if(args.length==0) {
					sender.sendMessage("/altars <set | list>");
					return true;
				}
				else if(args[0].equalsIgnoreCase("list")) {
					if(args.length == 2) {
						if(main.getGods().getAGod(args[1])!= null) {
							Bukkit.broadcastMessage(main.getGods().getAGod(args[1]).altarsToString());
						}
						else {
							Bukkit.broadcastMessage(args[1] + " doesn't exist");
							return false;
						}
					}
					else {
						Bukkit.broadcastMessage("/altar list <god>");
						return false;
					}
				}
				else if(args[0].equalsIgnoreCase("set")) {
					if(args.length >1) {
						if(args[1].equalsIgnoreCase("size")) {
							if(args.length == 5) {
								if(main.getGods().getAltarFromAltarAndGodName(args[3], args[4]) != null) {
									main.getGods().getAltarFromAltarAndGodName(args[3], args[4]).setSize( Integer.parseInt(args[2]));
									sender.sendMessage("size have been modifed");
									return true;
								}
								else {
									sender.sendMessage("altar or god doesn't exist");
									return false;
								}
							}
							else {
								sender.sendMessage("/altars set <size> <size> <altar> <god>");
								return false;
							}
						}
						sender.sendMessage("/altars set <size>");
						return false;
				}
				else if (args[0].equalsIgnoreCase("get")) {
					if(args.length >1) {
						if(args[1].equalsIgnoreCase("size")) {
							if(args.length == 3) {
								if(main.getGods().getAltarFromAltarAndGodName(args[3], args[4]) != null) {
									sender.sendMessage("Size of " + args[3] + " : " + main.getGods().getAltarFromAltarAndGodName(args[3], args[4]).getSize());
									return true;
								}
								else {
									sender.sendMessage("altar or god doesn't exist");
									return false;
								}
							}
						}
						sender.sendMessage("/altars get <size>");
						return false;
							
					}
					sender.sendMessage("/altars get <size>");
					return false;
					
					}
					else {
						sender.sendMessage("/altars <set | list | get>");
						return false;
					}
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) { 
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if((inv.toString().equalsIgnoreCase("§7Mon Menu"))) {
			event.setCancelled(true);
			if(current.getType()== Material.APPLE) {
				player.closeInventory();
				player.setGameMode(GameMode.SPECTATOR);
			}
			else if(current.getType()== Material.ANVIL) {
				player.closeInventory();
				player.getInventory().addItem(new ItemStack(Material.TNT,3));
			}
		}
		
			
	}

}
