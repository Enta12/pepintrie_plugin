package fr.pepintrie.pepintrieplugin.gods;

import org.bukkit.inventory.ItemStack;

import fr.pepintrie.pepintrieplugin.gods.objects.Goal;

public class EconomyGod extends God{
	
	private static final long serialVersionUID = 1L;

	public EconomyGod(String name) {
		super(name);
		color = "�a";
		type = GodsType.ECONOMY;
	}
	
	@Override
	protected void getGoals() {
		set10Goals(goals);
		//goal for size 1
		goals.get(0).add(new Goal("construisant une maison pour un villageois"));
		//goal for size 2
		goals.get(1).add(new Goal("construisant un petit temple"));
		goals.get(1).add(new Goal("arborant mes temples de ma bani�re"));
		//goal for size 3
		goals.get(2).add(new Goal("construisant un monument aux morts"));
		//goal for size 4
		goals.get(3).add(new Goal("construisant une place du march�"));
		goals.get(3).add(new Goal("capturant des prisoniers"));
		//goal for size 5
		goals.get(3).add(new Goal("faire une aisons pour chacun des villageois"));
		goals.get(3).add(new Goal("construisant une statue en mon honneur"));
		//goal for size 6
		goals.get(3).add(new Goal("construisant une crypte"));
		goals.get(3).add(new Goal("mettant en exposition deux armures en mailles"));
		//goal for size 7
		goals.get(3).add(new Goal("construisant une administration religieuse"));
		goals.get(3).add(new Goal("construisant un lieu de sacrifice"));
		//goal for size 8
		goals.get(3).add(new Goal("construisant une basilique"));
		//goal for size 9
		goals.get(3).add(new Goal("construisant une cath�drale"));
		//goal for size 10
		goals.get(3).add(new Goal("construisant une ville sainte"));
	}
	
	@Override
	public ItemStack createARelic() {
		//return Relic.getRelic(Material.EMERALD, random.nextInt(4)+1, getColorName(), "�meraude", "Pour ?", 0);
		return null;
	}



}
