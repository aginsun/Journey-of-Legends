package aginsun.taleofkingdoms.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import aginsun.taleofkingdoms.TaleOfKingdoms;
import aginsun.taleofkingdoms.entities.TileEntitySell;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSell extends BlockContainer
{
	protected BlockSell(int blockId)
	{	
		super(blockId, Material.rock);
		setBlockName("BlockSell");
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	@SideOnly(Side.CLIENT)
	public String getTextureFile()
	{
		return "aginsun/textures/blocks.png"; //TODO: add Texture Files
	}
	@SideOnly(Side.CLIENT)
    public int getBlockTextureFromSide(int i)
    { 
    	return 0;
    }
	
	@Override
	public TileEntity createNewTileEntity(World var1) 
	{
		return new TileEntitySell();
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
		TileEntitySell tile_entity = (TileEntitySell)world.getBlockTileEntity(x, y, z);
		if(tile_entity == null || par5EntityPlayer.isSneaking())
		{
			return false;
		}
		tile_entity.setPlayerName(par5EntityPlayer);
		par5EntityPlayer.openGui(TaleOfKingdoms.instance, 1, world, x, y, z);
		return true;
    	
    }
    
}
