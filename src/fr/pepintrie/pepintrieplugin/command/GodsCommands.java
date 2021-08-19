package fr.pepintrie.pepintrieplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pepintrie.pepintrieplugin.Main;

public class GodsCommands implements CommandExecutor{
	
	private Main main;
	
	public GodsCommands(Main main) {
		this.main = main;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
		if(tag.equalsIgnoreCase("gods")) {
			if (sender instanceof Player) {
				// /gods
				if(args.length==0) {
					sender.sendMessage("/gods <create | rename | delete | list>");
					return true;
				}
				else {
					if(args[0].equalsIgnoreCase("create")) {
						if(args.length == 3) {
							if(main.getGods().isAType(args[1])) {
								if(main.getGods().createAGod(args[1], args[2])) {
									sender.sendMessage(args[2] + " has been added");
									return true;
								}
								else {
									sender.sendMessage(args[2] + " already exist");
									return false;
								}
							}
							else {
								sender.sendMessage(args[1] + " is not a type\n/gods <create> <" + main.getGods().getTypesString() + "> <name>");
								return false;
							}
						}
						else {
							sender.sendMessage(args[1] + " is not a type\n/gods <create> <type> <name>");
							return false;
						}
					}
					else if (args[0].equalsIgnoreCase("delete")) {
						if(args.length == 2) {
							if(main.getGods().deleteAGod(args[1])) {
								sender.sendMessage(args[1] + " bas been deleted");
								return true;
							}
							else {
								sender.sendMessage(args[1] + " not exist");
								return false;
							}
						}
						else {
							sender.sendMessage("/gods <delete> <god>");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("rename")) {
						if(args.length == 3) {
							if(main.getGods().renameAGod(args[1], args[2])) {
								sender.sendMessage(args[1] + " has been renamed whith " + args[2]);
								return true;
							}
							else {
								sender.sendMessage(args[1] + " not exist or " + args[2] + " already exist");
								return false;
							}
						}
						else {
							sender.sendMessage("/gods <rename> <god> <name>");
							return false;
						}
			
					}
					else if(args[0].equalsIgnoreCase("list")) {
						sender.sendMessage(main.getGods().getListString());
						return true;
					}
					else {
						sender.sendMessage("/gods <create|rename|delete|list>");
						return false;
					}
				}
			}
			else return false;
		}
		else return false;
	}
}
