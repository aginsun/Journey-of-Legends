package aginsun.journey.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockReader extends Block
{
	public BlockReader(int par1) 
	{
		super(par1, Material.rock);
	}
	
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		/*File file = new File("FullBlocks.txt");
		File file1 = new File("RotationalBlocks.txt");
		BufferedWriter FullBlocks = null;
		BufferedWriter RotatedBlocks = null;
		try 
		{
			FullBlocks = new BufferedWriter(new FileWriter(file));
			for(int x = 0; x < 20; x++){
				for(int y = 0; y < 20; y++){
					for(int z = 0; z < 20; z++){		
						String Part1 = "Structures.add(new KingdomBlock(i + ";
						String Part2 = ", 3));";
						String newLine = System.getProperty("line.separator");
						int BlockID = world.getBlockId(i+x, j+y, k+z);
						int metadata = world.getBlockMetadata(i+x, j+y, k+z);
						if(BlockID != 0){
							FullBlocks.write(new StringBuilder().append(Part1).append(x).append(", j + ").append(y).append(", k +").append(z).append(", ").append(BlockID).append(", ").append(metadata).append(Part2).toString());
							FullBlocks.write(newLine);
						}
					}
				}
			}
			FullBlocks.close();

		} catch (IOException e){}
		return true;*/
		
		return true;
	}
}
