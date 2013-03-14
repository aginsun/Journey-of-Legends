package aginsun.taleofkingdoms.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class InitItems 
{
	public static Item ItemCoins;
	public static int ItemCoinsID;
	
	public static Item ItemExcalibur;
	public static int ItemExcaliburID;
	
	public static Item ItemAgBlade;
	public static int ItemAgBladeID;
	
	public static Item ItemExcaliburMace;
	public static int ItemExcaliburMaceID;
	
	public static Item ItemDebug;
	public static int ItemDebugID;
	
	public static void Init()
	{
		ItemCoins = new ItemCoins(ItemCoinsID).setItemName("coins");
		ItemExcalibur = new ItemExcaliburSword(ItemExcaliburID).setItemName("excalibur").setIconCoord(1, 0);
		ItemAgBlade = new ItemAgBlade(ItemAgBladeID).setItemName("Aginsuns Blade").setIconCoord(2, 0);
		ItemExcaliburMace = new ItemExcaliburMace(ItemExcaliburMaceID).setItemName("ExcaliburMace").setIconCoord(3, 0);
		ItemDebug = new ItemDebug(ItemDebugID).setTextureFile("/aginsun/textures/items.png").setIconCoord(4, 0).setItemName("DebugToK");
		
		languageRegisterers();
	}
	
	public static void languageRegisterers()
	{
		LanguageRegistry.addName(ItemCoins, "Coins");
		LanguageRegistry.addName(ItemExcalibur, "Excalibur");
		LanguageRegistry.addName(ItemAgBlade, "Aginsuns Blade");
		LanguageRegistry.addName(ItemExcaliburMace, "Excalibur Mace");
		LanguageRegistry.addName(ItemDebug, "Debug Tool ToK2");
	}
}
