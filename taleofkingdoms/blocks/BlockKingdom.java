package aginsun.taleofkingdoms.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.TaleOfKingdoms;
import aginsun.taleofkingdoms.core.helpers.GuiIds;
import aginsun.taleofkingdoms.entities.TileEntityKingdom;

public class BlockKingdom extends BlockContainer
{
	protected BlockKingdom(int par1) 
	{
		super(par1, Material.iron);
		this.setUnlocalizedName("BlockKingdom");
	}

	@Override
	public TileEntity createNewTileEntity(World var1) 
	{
		return new TileEntityKingdom();
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
		TileEntityKingdom tile_entity = (TileEntityKingdom)world.getBlockTileEntity(x, y, z);
		if(tile_entity == null || player.isSneaking())
		{
			return false;
		}
		tile_entity.setPlayerName(player);
		player.openGui(TaleOfKingdoms.instance, GuiIds.GUI_KINGDOM, world, x, y, z);
		return true;   	
    }
    
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("TaleOfKingdoms:BlockKingdom");
    }
}
