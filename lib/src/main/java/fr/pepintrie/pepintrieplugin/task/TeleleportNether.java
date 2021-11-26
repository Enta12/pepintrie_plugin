package fr.pepintrie.pepintrieplugin.task;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.pepintrie.pepintrieplugin.gods.objects.Altar;

public class TeleleportNether extends BukkitRunnable {

	private int timer = 2;
	private Altar altar;
	private Player player;
	
	public TeleleportNether(Player player, Altar altar) {
		this.player = player;
		this.altar = altar;
	}

	
	@Override
	public void run() {
		if(timer == 2) player.getWorld().spawnParticle(Particle.PORTAL, altar.getLocation(), 1500);
		timer -=1;
		if(timer == 0) {
			player.teleport(altar.getLocation());
			cancel();
		}
	}

}
