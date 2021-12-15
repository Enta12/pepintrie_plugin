package fr.pepintrie.pepintrieplugin.gods;
 
import org.bukkit.inventory.ItemStack;

import fr.pepintrie.pepintrieplugin.gods.objects.Goal;
import fr.pepintrie.pepintrieplugin.gods.objects.Relic;

public class NetherGod extends God{
	
	private static final long serialVersionUID = 1L;


	public NetherGod(String name) {
		super(name, Particle.PORTAL, 1500);
		this.particule = Particle.PORTAL
		this.particulesQuantity
		this.
		color = "&4";
		type = GodsType.NETHER;	
	}

	@Override
	protected void getGoals() {
		set10Goals(goals);
		//goal for size 1
		goals.get(0).add(new Goal("construisant un portail aménagé"));
		//goal for size 2
		goals.get(1).add(new Goal("construisant un petit temple"));
		goals.get(1).add(new Goal("arborant mes temples de ma banière"));
		//goal for size 3
		goals.get(2).add(new Goal("construisant un cimetière dans le neither"));
		//goal for size 4
		goals.get(3).add(new Goal("construisant une office de magie"));
		goals.get(3).add(new Goal("capturant des prisoniers"));
		//goal for size 5
		goals.get(3).add(new Goal("construisant une statue en mon honneur"));
		//goal for size 6
		goals.get(3).add(new Goal("construisant une crypte"));
		goals.get(3).add(new Goal("mettant en exposition deux armures en neitherites"));
		//goal for size 7
		goals.get(3).add(new Goal("construisant une administration religieuse"));
		goals.get(3).add(new Goal("construisant un lieu de sacrifice"));
		//goal for size 8
		goals.get(3).add(new Goal("construisant une basilique"));
		//goal for size 9
		goals.get(3).add(new Goal("construisant une cathédrale"));
		//goal for size 10
		goals.get(3).add(new Goal("construisant une ville sainte"));
		
	}


	@Override
	public ItemStack createARelic() {
		return Relic.getNetherRelic(random.nextInt(10)+1, getColorName(), "Compas", "Pour se téléporter à son hôtel", random.nextInt(4)+1);
	}



}
