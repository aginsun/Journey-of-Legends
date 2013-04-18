package aginsun.taleofkingdoms.blocks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import aginsun.taleofkingdoms.kingdom.KingdomBlock;

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
		File file = new File("Readed.txt");
		BufferedWriter EndProduct = null;
		try 
		{
			EndProduct = new BufferedWriter(new FileWriter(file));
			for(int x = 0; x < 50; x++)
			{
				for(int y = 0; y < 50; y++)
				{
					for(int z = 0; z < 50; z++)
					{				
						String Part1 = "Structures.add(new KingdomBlock(";
						String Part2 = ", 3));";
						String newLine = System.getProperty("line.separator");
						int BlockID = world.getBlockId(i+x, j+y, k+z);
						int metadata = world.getBlockMetadata(i+x, j+y, k+z);
						if(BlockID != 0)
						{
							EndProduct.write(new StringBuilder().append(Part1).append(x).append(", ").append(y).append(", ").append(z).append(", ").append(BlockID).append(", ").append(metadata).append(Part2).toString());
							EndProduct.write(newLine);
						}
					}
				}
			}
			EndProduct.close();
		} catch (IOException e){}
		return true;
	}
}
