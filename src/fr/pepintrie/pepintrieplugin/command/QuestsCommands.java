package fr.pepintrie.pepintrieplugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pepintrie.pepintrieplugin.Main;
import fr.pepintrie.pepintrieplugin.gods.objects.Altar;
import fr.pepintrie.pepintrieplugin.gods.objects.Quest;

public class QuestsCommands implements CommandExecutor {
	
	private Main main;

	public QuestsCommands(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String tag, String[] args) {
		if(tag.equalsIgnoreCase("quests")) {
			if (sender instanceof Player) {
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("list")) {
						if(args.length == 2) {
							Altar altar = main.getGods().getAltarFromAltarName(args[1]);
							if(altar != null) {
								Bukkit.broadcastMessage("Linked quests are : ");
								for(Quest quest : altar.getQuests()) {
									Bukkit.broadcastMessage(quest.getId() + " :" + quest.getDescription());
								}
								return true;
							}
							else {
								Bukkit.broadcastMessage(args[1] + " is not an altar");
								return false;
							}
							
						}
						else {
							Bukkit.broadcastMessage("/quests list <altar>");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("create")) {
						if(args.length == 3) {
							if(main.getGods().getAGod(args[1]) != null) {
								Altar altar =main.getGods().getAltarFromAltarAndGodName(args[2], args[1]);
								if(altar != null) {
									boolean notPlayer = true;
									for(Player player : Bukkit.getOnlinePlayers()) {
										if(player.getName().equalsIgnoreCase(altar.getPlayerName())) {
											//Random random = new Random();
											//int event = random.nextInt(100);
											main.getGods().getAltarFromAltarAndGodName(args[2], args[1]).createNewQuest(player, 5);
											return true;
										}
									}
									if (notPlayer) {
										Bukkit.broadcastMessage(args[3] + " is not online");
										return false;
									}
								}
								else {
									Bukkit.broadcastMessage(args[2] + " is not an altar");
									return false;
								}
							}
							else {
								Bukkit.broadcastMessage(args[1] + " is not a god");
								return false;
							}
						}
						else {
							Bukkit.broadcastMessage("/quests create <god> <altar>");
							return false;
						}
					}
					else if(args[0].equalsIgnoreCase("finish")) {
						if(args.length == 4) {
							if(main.getGods().getAGod(args[1]) != null) {
								Altar altar = main.getGods().getAltarFromAltarAndGodName(args[2], args[1]);
								if(altar != null) {
									altar.finish(Integer.valueOf(args[3]));
									return true;
								}
								else {
									Bukkit.broadcastMessage(args[2] + " is not an altar");
									return false;
								}
							}
							else {
								Bukkit.broadcastMessage(args[1] + " is not a god");
								return false;
							}
						}
						else {
							Bukkit.broadcastMessage("/quest finish <god> <altar> <quest id>");
							return false;
						}
					}
				}
			}
		}
		Bukkit.broadcastMessage("/quests < list | create | finish >");
		return false;
	}

}
