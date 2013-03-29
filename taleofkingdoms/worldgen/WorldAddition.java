package aginsun.taleofkingdoms.worldgen;

import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class WorldAddition 
{
	private static World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
	
	public static boolean setBlockWithMetadata(int x, int y, int z, int BlockID, int MetaData)
	{
		return world.setBlock(x, y, z, BlockID, MetaData, 0x02);
	}
}
