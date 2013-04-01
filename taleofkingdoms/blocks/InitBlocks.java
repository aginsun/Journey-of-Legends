package aginsun.taleofkingdoms.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class InitBlocks 
{
	public static Block BlockSell;
	public static Block BlockKingdom;
	
	public static int BlockSellID;
	public static int BlockKingdomID;
	
	public static void Init()
	{
		BlockSell = new BlockSell(BlockSellID).setResistance(100000.0F).setHardness(120000.0F).setBlockUnbreakable();
		GameRegistry.registerBlock(BlockSell, "BlockSell");
		BlockKingdom = new BlockKingdom(BlockKingdomID).setBlockUnbreakable().setCreativeTab(CreativeTabs.tabBlock);
		GameRegistry.registerBlock(BlockKingdom, "BlockKingdom");
	}
}
