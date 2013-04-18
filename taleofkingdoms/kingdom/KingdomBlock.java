package aginsun.taleofkingdoms.kingdom;

import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class KingdomBlock
{
	public int x;
	public int y;
	public int z;
	public int BlockID;
	public int metadata;
	
	public KingdomBlock(int x, int y, int z, int BlockID, int metadata, int flag)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.BlockID = BlockID;
		this.metadata = metadata;
	}
}
