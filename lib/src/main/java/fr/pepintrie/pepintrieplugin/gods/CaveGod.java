package fr.pepintrie.pepintrieplugin.gods;

import org.bukkit.inventory.ItemStack;

import fr.pepintrie.pepintrieplugin.gods.objects.Goal;
import fr.pepintrie.pepintrieplugin.gods.objects.Relic;

public class CaveGod extends God {

	private static final long serialVersionUID = 1L;


	public CaveGod(String name) {
		super(name);
		color = "&0";
		type = GodsType.CAVE;	
	}


	@Override
	protected void getGoals() {
		set10Goals(goals);
		//goal for size 1
		goals.get(0).add(new Goal("Aménager un petit coin dans une grotte"));
		//goal for size 2
		goals.get(1).add(new Goal("construisant un temple modeste"));
		goals.get(1).add(new Goal("Déployant ma bannière"));
		//goal for size 3
		goals.get(2).add(new Goal("construisant un cimetière dans les souterrains"));
		//goal for size 4
		goals.get(3).add(new Goal("construisant une porte dans les souterrains"));
		goals.get(3).add(new Goal("capturant des prisoniers"));
		//goal for size 5
		goals.get(4).add(new Goal("construisant une statue en mon honneur"));
		//goal for size 6
		goals.get(5).add(new Goal("construisant une crypte"));
		goals.get(5).add(new Goal("mettant en exposition les richesses des entrailles de la terre"));
		//goal for size 7
		goals.get(6).add(new Goal("construisant une administration religieuse"));
		goals.get(6).add(new Goal("construisant un lieu de sacrifice"));
		//goal for size 8
		goals.get(7).add(new Goal("construisant une basilique"));
		//goal for size 9
		goals.get(8).add(new Goal("construisant une cathédrale"));
		//goal for size 10
		goals.get(9).add(new Goal("construisant une ville sainte"));
		
	}


	@Override
	public ItemStack createARelic() {
		return Relic.getNetherRelic(random.nextInt(10)+1, getColorName(), "Compas");
	}
}