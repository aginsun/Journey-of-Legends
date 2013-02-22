package aginsun.taleofkingdoms.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class InitBlocks 
{
	public static Block BlockSell;
	public static Block BlockKingdom;
	
	public static int BlockSellID;
	
	public static void Init()
	{
		BlockSell = new BlockSell(BlockSellID).setResistance(100000.0F).setHardness(120000.0F).setBlockUnbreakable();
		GameRegistry.registerBlock(BlockSell, "BlockSell");
	}
}
