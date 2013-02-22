package aginsun.taleofkingdoms.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class InitItems 
{
	public static Item ItemCoins;
	public static int ItemCoinsID;
	
	public static Item ItemExcalibur;
	public static int ItemExcaliburID;
	
	public static void Init()
	{
		ItemCoins = new ItemCoins(ItemCoinsID).setItemName("coins");
		ItemExcalibur = new ItemExcalibur(ItemExcaliburID).setItemName("excalibur").setIconCoord(1, 0);
		
		languageRegisterers();
	}
	
	public static void languageRegisterers()
	{
		LanguageRegistry.addName(ItemCoins, "Coins");
		LanguageRegistry.addName(ItemExcalibur, "Excalibur");
	}
}
