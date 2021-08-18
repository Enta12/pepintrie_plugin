package fr.pepintrie.pepintrieplugin.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LicenceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
		if(tag.equalsIgnoreCase("licence")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(args.length == 1) {
					Inventory inv = player.getInventory();
					if(inv.containsAtLeast(new ItemStack(Material.PAPER), 1)) {
						int paperSlot = inv.first(Material.PAPER);
						int emptySlot = inv.firstEmpty();
						int paperAmount = inv.getItem(paperSlot).getAmount();
						if(emptySlot != -1 || paperAmount == 1) {
							inv.getItem(paperSlot).setAmount(paperAmount-1);
							ItemStack licence = new ItemStack(Material.PAPER);
							ItemMeta licenceMeta = licence.getItemMeta();
							Date date = new Date();
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							licenceMeta.setDisplayName("§4Licence de " + args[0]);
							licenceMeta.setLore(Arrays.asList("§c" + args[0] + "§f est autorisé a vendre au sein" , "§fde son magasin 10 articles au choix", "§fLicence délivré par §c" + player.getPlayerListName(), "§fFait le " + dateFormat.format(date) ));
							licence.setItemMeta(licenceMeta);
							inv.addItem(licence);
							player.sendMessage("Licence for " + args[0] + " has been created");
							return true;
						}
						else {
							player.sendMessage("You haven't any place in your inventory, you need a empty slot");
							return false;
						}
					}
					else {
						player.sendMessage("You haven't any paper, please take a paper");
						return false;
					}
				}
				else {
					player.sendMessage("/licence <player>");
					return false;
				}
				
			}
			else return false;
		}
		else return false;
	}

}
