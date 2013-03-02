package aginsun.taleofkingdoms.core;

import aginsun.taleofkingdoms.blocks.InitBlocks;
import aginsun.taleofkingdoms.items.InitItems;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigFileToK 
{
	public static boolean GuildSpawning;
	public static int xLocation;
	public static int yLocation;
	public static int zLocation;
	
	public static void config(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		
		InitItems.ItemCoinsID = config.getItem("Coins", 22125).getInt();
		InitBlocks.BlockSellID = config.getBlock("SellBlock", 1750).getInt();
		InitItems.ItemExcaliburID = config.getItem("Excalibur", 22126).getInt();
		InitItems.ItemAgBladeID = config.getItem("Aginsuns Blade", 22127).getInt();
		
		GuildSpawning = config.get("Guild", "Disable Spawning", false).getBoolean(false);
		xLocation = config.get("Guild", "X location", 0).getInt();
		yLocation = config.get("Guild", "Y location", 0).getInt();
		zLocation = config.get("Guild", "Z location", 0).getInt();
		
		config.save();
	}
}
