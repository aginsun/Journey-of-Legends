package aginsun.journey.universal.items;

import net.minecraft.item.Item;
import aginsun.journey.universal.utils.Utils;
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
	
	public static Item itemShuricken;
	public static int itemShurickenID;
	
	public static Item itemRedClaw;
	public static int itemRedClawID;
	
	public static void Init()
	{
		ItemCoins = new ItemCoins(ItemCoinsID).setUnlocalizedName("Coins").func_111206_d(ModID + ":coins");
		ItemExcalibur = new ItemExcaliburSword(ItemExcaliburID).setUnlocalizedName("Excalibur").func_111206_d(ModID+":"+"Sword15");
		ItemAgBlade = new ItemAgBlade(ItemAgBladeID).setUnlocalizedName("AgBlade").func_111206_d(ModID+":"+"AginsunsBlade");
		ItemExcaliburMace = new ItemExcaliburMace(ItemExcaliburMaceID).setUnlocalizedName("ExMace").func_111206_d(ModID+":"+"ExcaliburMace");
		ItemDebug = new ItemDebug(ItemDebugID).setUnlocalizedName("Debug").func_111206_d(ModID+":"+"DebugToK");
		itemRedClaw = new ItemRedClaw(itemRedClawID).setUnlocalizedName("RedClaw").func_111206_d(ModID+":redClaw");
		itemShuricken = new ItemShuricken(itemShurickenID).setUnlocalizedName("shurickenStar").func_111206_d(ModID+":shurickenStar");
		languageRegisterers();
	}
	
	public static void languageRegisterers()
	{
		LanguageRegistry.addName(ItemCoins, "Coins");
		LanguageRegistry.addName(ItemExcalibur, "Excalibur");
		LanguageRegistry.addName(ItemAgBlade, "Aginsuns Blade");
		LanguageRegistry.addName(ItemExcaliburMace, "Excalibur Mace");
		LanguageRegistry.addName(ItemDebug, "Debug Tool ToK2");
		LanguageRegistry.addName(itemRedClaw, "Red Claw");
		LanguageRegistry.addName(itemShuricken, "Shuricken Star");
	}
}
