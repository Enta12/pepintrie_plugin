package fr.pepintrie.pepintrieplugin.gods.mob;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.level.World;

public class Priest extends EntityVillager {

	public Priest(Location loc, String color) {
		super(EntityTypes.aV, (World) Bukkit.getWorld("world"));
		this.setPosition(loc.getX(), loc.getY(), loc.getZ());
		this.setCustomName(new ChatComponentText(color + "Name Test"));
		this.setCustomNameVisible(true);
		this.setHealth(Integer.MAX_VALUE);
	}
	//create entity


}
