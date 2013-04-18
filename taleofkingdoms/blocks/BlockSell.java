package aginsun.taleofkingdoms.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.TaleOfKingdoms;
import aginsun.taleofkingdoms.entities.TileEntitySell;

public class BlockSell extends BlockContainer
{
	protected BlockSell(int blockId)
	{	
		super(blockId, Material.rock);
		this.setUnlocalizedName("BlockSell");
		this.setCreativeTab(CreativeTabs.tabBlock);
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
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("TaleOfKingdoms:BlockSell");
    }
}
