package fr.pepintrie.pepintrieplugin.gods;

import org.bukkit.inventory.ItemStack;

import fr.pepintrie.pepintrieplugin.gods.objects.Goal;
import fr.pepintrie.pepintrieplugin.gods.objects.Relic;

public class SeaGod extends God{


	private static final long serialVersionUID = 1L;

	public SeaGod(String name) {
		super(name);
		color = "�9";
		type = GodsType.SEA;
	}

	@Override
	protected void getGoals() {
		set10Goals(goals);
		//goal for size 1
		goals.get(0).add(new Goal("objectif1"));
		//goal for size 2
		goals.get(1).add(new Goal("objectif2"));
		//goal for size 3
		goals.get(2).add(new Goal("objectif3"));
		//goal for size 4
		goals.get(3).add(new Goal("objectif4"));
		//goal for size 5
		goals.get(4).add(new Goal("objectif5"));
		//goal for size 6
		goals.get(5).add(new Goal("objectif6"));
		//goal for size 7
		goals.get(6).add(new Goal("objectif7"));
		//goal for size 8
		goals.get(7).add(new Goal("objectif8"));
		//goal for size 9
		goals.get(8).add(new Goal("objectif9"));
		//goal for size 10
		goals.get(9).add(new Goal("objectif10"));
		
	}
	
	@Override
	public ItemStack createARelic() {
		return Relic.getSeaRelic(random.nextInt(3)+1, getColorName(), "Coeur ", random.nextInt(20)+1);
	}
}
