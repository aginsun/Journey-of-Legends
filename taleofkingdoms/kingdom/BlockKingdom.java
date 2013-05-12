package aginsun.taleofkingdoms.kingdom;

public class BlockKingdom 
{
	public int BlockID;
	public int metadata;
	public int x;
	public int y;
	public int z;
	
	public BlockKingdom(int BlockID, int metadata, int x, int y, int z)
	{
		this.BlockID = BlockID;
		this.metadata = metadata;
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
