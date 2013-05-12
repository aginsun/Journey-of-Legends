package aginsun.taleofkingdoms.kingdom;

import java.util.ArrayList;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.world.World;

public abstract class KingdomStructure
{
	protected static ArrayList<BlockKingdom> BlockList = new ArrayList<BlockKingdom>();
	private static int Speed = 1;
	private static int buildCounter;
	private int index;
	protected static World world;
	protected static int x, y, z;
	private static boolean StartBuilding;
	
	public static void update()
	{
		if(!BlockList.isEmpty())
		{
			if(StartBuilding)
			{	
				for(int i = 0; i < Speed; i ++)
					createBuilding();
			}
		}
	}
	
	public boolean startBuilding()
	{
		return StartBuilding = true;
	}
	
	public void setLocation(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	private static void createBuilding()
	{
		createBuildingN();
		
		buildCounter++;
	}
	
	private static void createBuildingN()
	{
		world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
		if(buildCounter < BlockList.size())
		{
			BlockKingdom block = (BlockKingdom)BlockList.get(buildCounter);
			world.setBlock(x + block.x, y + block.y, z + block.z,block.BlockID, block.metadata, 0x02);
		}
		else
		{
			StartBuilding = false;
			BlockList.clear();
		}
	}
}
