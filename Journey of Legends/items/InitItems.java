package aginsun.journey.items;

import aginsun.journey.util.Utils;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class InitItems 
{
	public static String ModID = Utils.modID;
	
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
		ItemCoins = new ItemCoins(ItemCoinsID).setUnlocalizedName(ModID+":"+"Coins");
		ItemExcalibur = new ItemExcaliburSword(ItemExcaliburID).setUnlocalizedName(ModID+":"+"ExcaliburSword");
		ItemAgBlade = new ItemAgBlade(ItemAgBladeID).setUnlocalizedName(ModID+":"+"AginsunsBlade");
		ItemExcaliburMace = new ItemExcaliburMace(ItemExcaliburMaceID).setUnlocalizedName(ModID+":"+"ExcaliburMace");
		ItemDebug = new ItemDebug(ItemDebugID).setUnlocalizedName(ModID+":"+"DebugToK");
		
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
