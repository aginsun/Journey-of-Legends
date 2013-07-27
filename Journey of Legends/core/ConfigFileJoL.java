package aginsun.journey.core;

import net.minecraftforge.common.Configuration;
import aginsun.journey.blocks.InitBlocks;
import aginsun.journey.items.InitItems;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigFileJoL 
{
	public static boolean GuildSpawning;
	public static int xLocation;
	public static int yLocation;
	public static int zLocation;
	
	public static void config(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		InitBlocks.BlockSellID = config.getBlock("SellBlock", 1750).getInt();
		InitBlocks.BlockKingdomID = config.getBlock("KingdomBlock", 1751).getInt();
		
		InitItems.ItemCoinsID = config.getItem("Coins", 22125).getInt();
		InitItems.ItemExcaliburID = config.getItem("Excalibur", 22126).getInt();
		InitItems.ItemAgBladeID = config.getItem("Aginsuns Blade", 22127).getInt();
		InitItems.ItemExcaliburMaceID = config.getItem("Excalibur Mace", 22128).getInt();
		InitItems.ItemDebugID = config.getItem("Debug Tool ToK2", 22129).getInt();
		InitItems.itemRedClawID = config.getItem("RedClaw", 22130).getInt();
		InitItems.itemShurickenID = config.getItem("Shuricken Star", 22131).getInt();

		config.save();
	}
}
