package fr.pepintrie.pepintrieplugin.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import fr.pepintrie.pepintrieplugin.Main;
import fr.pepintrie.pepintrieplugin.gods.God;
import fr.pepintrie.pepintrieplugin.gods.NetherGod;

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
					sender.sendMessage("/gods <create|rename|delete>");
					return true;
				}
				// /gods create
				else {
					if(args[0].equalsIgnoreCase("create")) {
						if(args.length == 3) {
							boolean isAlreadyCreated = false;
							for(God god : main.getGods()) {
								if(god.getName().equalsIgnoreCase(args[2])) {
									isAlreadyCreated = true;
									break;
								}
							}
							if(isAlreadyCreated) {
								sender.sendMessage(args[2] + " already exist");
								return false;
							}
							else {
								if (args[1].equalsIgnoreCase("nether")){
									main.getGods().add(new NetherGod(args[2]));
									sender.sendMessage(main.getGods().get(main.getGods().size()-1).getColorName() + " has been added");
									return true;
								}
								else {
									sender.sendMessage("/gods <create> <"+ God.getTypes() +"> <name>");
									return false;
								}
							}
						}
						else {
							sender.sendMessage("/gods <create> <type of god> <name>");
							return false;
						}
					}
					else if (args[0].equalsIgnoreCase("delete")) {
						if(args.length == 2) {
							List<God> gods = main.getGods();
							for(int i = 0; i < gods.size(); i++) {
								if(gods.get(i).getName().equalsIgnoreCase(args[1])) {
									sender.sendMessage(gods.get(i).getColorName() + " bas been deleted");
									gods.remove(i);
									return true;
								}
							}
							sender.sendMessage(args[1] + " not exist");
							return false;
						}
						else {
							sender.sendMessage("/gods <delete> <god>");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("rename")) {
						if(args.length == 3) {
							List<God> gods = main.getGods();
							for(int i = 0; i < gods.size(); i++) {
								if(gods.get(i).getName().equalsIgnoreCase(args[1])) {
									String oldName = gods.get(i).getColorName();
									gods.get(i).setName(args[2]);
									sender.sendMessage(oldName + " bas been renamed by " + gods.get(i).getColorName());
									return true;
								}
							}
							sender.sendMessage(args[1] + " not exist");
							return false;
						}
						else {
							sender.sendMessage("/gods <rename> <god> <name>");
							return false;
						}
			
					}
					else if(args[0].equalsIgnoreCase("list")) {
						String str = "Gods are :\n";
						for(God god : main.getGods()) {
							str += god.getColorName() + " " + god.getType() + " god\n";
						}
						sender.sendMessage(str);
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
