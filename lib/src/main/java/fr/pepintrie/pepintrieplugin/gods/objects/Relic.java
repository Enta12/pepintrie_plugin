package fr.pepintrie.pepintrieplugin.gods.objects;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class Relic {
	
	public Relic(){
	}
	
	public static ItemStack getNetherRelic(int utilisationLeft, String godName, String altar,String object,  int powerNeed) {
		ItemStack relicItem = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta customM = relicItem.getItemMeta();
		
		
		customM.setDisplayName(object + " de " + godName);
		customM.addEnchant(Enchantment.ARROW_INFINITE, 255, true);
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customM.setLore(Arrays.asList("Cette objet est une relique de " + godName, "Pour se téléporter sur l'altar de " + altar, "L'utilisation necessite " + powerNeed, utilisationLeft + " utilisations restante sur " + utilisationLeft));
		relicItem.setItemMeta(customM);
		return relicItem;
	}
	
	public static ItemStack getSeaRelic(int utilisationLeft, String godName, String object,  int powerNeed) {
		ItemStack relicItem = new ItemStack(Material.COMPASS, 1);
		ItemMeta customM = relicItem.getItemMeta();
		
		
		customM.setDisplayName(object + " de " + godName);
		customM.addEnchant(Enchantment.LOOT_BONUS_MOBS, 255, true);
		
		Random random = new Random();
		int x1 = random.nextInt(5);
		int x2 = random.nextInt(99);
		int x3 = random.nextInt(99);
		
		int z1 = random.nextInt(5);
		int z2 = random.nextInt(99);
		int z3 = random.nextInt(99);
		
		
		int y = random.nextInt(255);
		
		//X
		customM.addEnchant(Enchantment.ARROW_DAMAGE, x1 - 2, true);
		customM.addEnchant(Enchantment.ARROW_FIRE, x2, true);
		customM.addEnchant(Enchantment.LOYALTY,  x3, true);
		
		//Z
		customM.addEnchant(Enchantment.ARROW_KNOCKBACK, z1-3, true);
		customM.addEnchant(Enchantment.BINDING_CURSE, z2, true);
		customM.addEnchant(Enchantment.CHANNELING, z3, true);
		
		//y
		customM.addEnchant(Enchantment.FIRE_ASPECT, y, true);
		
		//getBiome
		Biome biome = Bukkit.getWorld("world").getBiome(x1*10000+x2*100+x3, y, z1*10000+z2*100+z3);
		String biomeString;
		if(biome == Biome.BADLANDS || biome == Biome.WOODED_BADLANDS_PLATEAU|| biome == Biome.BADLANDS_PLATEAU || biome == Biome.ERODED_BADLANDS || biome == Biome.MODIFIED_BADLANDS_PLATEAU || biome == Biome.MODIFIED_WOODED_BADLANDS_PLATEAU) {
			biomeString ="un badland";
		}
		else if (biome == Biome.BAMBOO_JUNGLE || biome == Biome.BAMBOO_JUNGLE_HILLS || biome == Biome.JUNGLE || biome == Biome.JUNGLE_EDGE || biome == Biome.JUNGLE_HILLS|| biome == Biome.MODIFIED_JUNGLE|| biome == Biome.MODIFIED_JUNGLE_EDGE) {
			biomeString ="une jungle";
		}
		else if (biome == Biome.BASALT_DELTAS) {
			biomeString ="un biome de basalte";
		}
		else if (biome == Biome.BIRCH_FOREST || biome == Biome.BIRCH_FOREST_HILLS) {
			biomeString ="une forêt de bouleau";
				}
		else if (biome == Biome.COLD_OCEAN || biome == Biome.WARM_OCEAN ||biome == Biome.OCEAN || biome == Biome.DEEP_COLD_OCEAN || biome == Biome.DEEP_FROZEN_OCEAN || biome == Biome.DEEP_LUKEWARM_OCEAN || biome == Biome.DEEP_OCEAN || biome == Biome.DEEP_WARM_OCEAN|| biome == Biome.FROZEN_OCEAN || biome == Biome.LUKEWARM_OCEAN) {
			biomeString ="un océan";
		}
		else if (biome == Biome.CRIMSON_FOREST) {
			biomeString ="une forêt de crimson";
		}
		else if (biome == Biome.DARK_FOREST || biome == Biome.DARK_FOREST_HILLS) {
			biomeString ="une forêt sombre";
		}
		else if (biome == Biome.DESERT || biome == Biome.DESERT_HILLS || biome == Biome.DESERT_LAKES) {
			biomeString ="un desert";
		}
		else if (biome == Biome.BEACH || biome == Biome.SNOWY_BEACH) {
			biomeString ="une plage";
		}
		
		else if (biome == Biome.DRIPSTONE_CAVES) {
			biomeString ="une cave de dripstone";
		}
		else if (biome == Biome.END_BARRENS || biome == Biome.END_HIGHLANDS || biome == Biome.END_MIDLANDS  || biome == Biome.THE_END || biome == Biome.SMALL_END_ISLANDS) {
			biomeString ="l'end";
		}
		else if (biome == Biome.FLOWER_FOREST) {
			biomeString ="une forêt de fleurs";
		}
		else if (biome == Biome.FOREST) {
			biomeString ="une forêt";
		}
		else if (biome == Biome.FROZEN_RIVER || biome == Biome.RIVER) {
			biomeString ="une rivière";
		}
		else if (biome == Biome.GIANT_SPRUCE_TAIGA || biome == Biome.GIANT_SPRUCE_TAIGA_HILLS || biome == Biome.GIANT_TREE_TAIGA || biome == Biome.GIANT_TREE_TAIGA_HILLS) {
			biomeString ="une taiga géante";
		}
		else if (biome == Biome.SNOWY_TAIGA || biome == Biome.SNOWY_TAIGA_HILLS || biome == Biome.SNOWY_TAIGA_MOUNTAINS) {
			biomeString ="une taiga eneigée";
		}
		else if (biome == Biome.ICE_SPIKES) {
			biomeString ="une plaine de piques de glaces";
		}
		else if (biome == Biome.LUSH_CAVES) {
			biomeString ="une cave luxuriante";
		}
		else if (biome == Biome.MOUNTAIN_EDGE || biome == Biome.MOUNTAINS || biome == Biome.SNOWY_MOUNTAINS|| biome == Biome.WOODED_MOUNTAINS|| biome == Biome.WOODED_HILLS || biome == Biome.GRAVELLY_MOUNTAINS || biome == Biome.MODIFIED_GRAVELLY_MOUNTAINS) {
			biomeString ="une montagne";
		}
		else if (biome == Biome.MUSHROOM_FIELD_SHORE || biome == Biome.MUSHROOM_FIELDS) {
			biomeString ="une ile champignon";
		}
		else if (biome == Biome.NETHER_WASTES) {
			biomeString ="les déchets du nether";
		}
		else if (biome == Biome.PLAINS || biome == Biome.SUNFLOWER_PLAINS) {
			biomeString ="une plaine";
		}
		else if (biome == Biome.SAVANNA || biome == Biome.SAVANNA_PLATEAU || biome == Biome.SHATTERED_SAVANNA || biome == Biome.SHATTERED_SAVANNA_PLATEAU) {
			biomeString ="une savane";
		}
		else if (biome == Biome.SNOWY_TUNDRA) {
			biomeString ="une toundra";
		}
		else if (biome == Biome.SOUL_SAND_VALLEY) {
			biomeString ="une valley de sable d'âme";
		}
		else if (biome == Biome.STONE_SHORE) {
			biomeString ="une falaise";
		}
		else if (biome == Biome.SWAMP ||biome == Biome.SWAMP_HILLS) {
			biomeString ="un marécage";
		}
		else if (biome == Biome.TAIGA ||biome == Biome.TAIGA_HILLS ||biome == Biome.TAIGA_MOUNTAINS) {
			biomeString ="une taiga";
		}
		else if (biome == Biome.TALL_BIRCH_FOREST || biome == Biome.TALL_BIRCH_HILLS) {
			biomeString ="une forêt de bouleau";
		}
		else if (biome == Biome.WARPED_FOREST) {
			biomeString ="une forêt déformée";
		}
		else if (biome == Biome.THE_VOID) {
			biomeString ="le vide";
		}
		else {
			biomeString ="ERREUR merci de contacter un admin";
		}
		
		
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customM.setLore(Arrays.asList("Cette objet est une relique de " + godName, "Peut pointer vers " + biomeString,  "L'utilisation necessite " + powerNeed,  utilisationLeft + " utilisations restante sur " + utilisationLeft));
		relicItem.setItemMeta(customM);
		return relicItem;
	}
	
	public static ItemStack getCaveRelic(int utilisationLeft, String godName, String altar,String object) {
		ItemStack relicItem = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta customM = relicItem.getItemMeta();
		
		Random random = new Random();
		int lenght = random.nextInt(25);
		int width = random.nextInt(12);
		int height = random.nextInt(12);
		int powerNeed = 1 + ((lenght + width + height)/10);
		String zone = ((width*2)+1) + " de large, " + ((lenght*2)+1) + " de hauteur," + ((height*2)+1) + " de longueur,";

		//set enchantment
		
		customM.setDisplayName(object + " de " + godName);
		customM.addEnchant(Enchantment.ARROW_INFINITE, 255, true); //TODO
		customM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		customM.setLore(Arrays.asList("Cette objet est une relique de " + godName, "Pour casser une zone en face de  " + zone + "centré sur vous", "L'utilisation necessite " + powerNeed, utilisationLeft + " utilisations restante sur " + utilisationLeft));
		relicItem.setItemMeta(customM);
		return relicItem;
	}


}
