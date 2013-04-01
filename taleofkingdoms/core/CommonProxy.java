package aginsun.taleofkingdoms.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.client.guis.GuiKingdom;
import aginsun.taleofkingdoms.client.guis.GuiSell;
import aginsun.taleofkingdoms.containers.ContainerKingdom;
import aginsun.taleofkingdoms.containers.ContainerSell;
import aginsun.taleofkingdoms.core.helpers.GuiIds;
import aginsun.taleofkingdoms.entities.TileEntityKingdom;
import aginsun.taleofkingdoms.entities.TileEntitySell;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{
	public void RegisterRenderers()
	{
		
	}

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
    {
    	switch(id)
    	{
    		case GuiIds.GUI_SELL :
    			return new ContainerSell((TileEntitySell) world.getBlockTileEntity(x, y, z), player.inventory);
    		case GuiIds.GUI_KINGDOM :
    			return new ContainerKingdom((TileEntityKingdom) world.getBlockTileEntity(x, y, z), player.inventory);
            default:
            	return null;
    	}
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,int x, int y, int z) 
    {
    	switch(id)
    	{
    		case GuiIds.GUI_SELL :
    			return new GuiSell(player.inventory, (TileEntitySell) world.getBlockTileEntity(x, y, z));
    		case GuiIds.GUI_KINGDOM :
    			return new GuiKingdom(player.inventory, (TileEntityKingdom) world.getBlockTileEntity(x, y, z));
    		default:
    			return null;
    	}
    }
}
