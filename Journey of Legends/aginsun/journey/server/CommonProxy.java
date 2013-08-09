package aginsun.journey.server;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import aginsun.journey.client.guis.GuiSell;
import aginsun.journey.universal.entities.TileEntitySell;
import aginsun.journey.universal.inventory.ContainerSell;
import aginsun.journey.universal.utils.GuiIds;
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
    		default:
    			return null;
    	}
    }

	public void registerPostInit() 
	{
		
	}
}
