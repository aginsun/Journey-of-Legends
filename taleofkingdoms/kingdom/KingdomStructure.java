package aginsun.taleofkingdoms.kingdom;

import java.util.ArrayList;

import net.minecraft.world.World;

import cpw.mods.fml.common.FMLCommonHandler;

import aginsun.taleofkingdoms.blocks.InitBlocks;

public class KingdomStructure 
{
	private static ArrayList<KingdomBlock> Structures = new ArrayList<KingdomBlock>();
	private static World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
	public static void Update()
	{
		KingdomBuildingWeird.createBuilding();
		for(int x = 0; x < KingdomBuildingWeird.getArray().size(); x++)
		{
			KingdomBlock block = KingdomBuildingWeird.getArray().get(x);
			world.setBlock(block.x, 10 + block.y, block.z, block.BlockID, block.metadata, 0x02);
		}
	}
}
