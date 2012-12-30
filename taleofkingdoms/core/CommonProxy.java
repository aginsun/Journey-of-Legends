package aginsun.taleofkingdoms.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.client.core.ClientTickHandler;
import aginsun.taleofkingdoms.client.guis.GuiSell;
import aginsun.taleofkingdoms.containers.ContainerSell;
import aginsun.taleofkingdoms.core.handlers.CommonTickHandler;
import aginsun.taleofkingdoms.core.helpers.GuiIds;
import aginsun.taleofkingdoms.entities.TileEntitySell;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler
{
	public void RegisterRenderers()
	{
		
	}
	
	public static void Init()
	{

	}

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
    {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(id == GuiIds.GUI_SELL && tileEntity != null)
            {
                    return new ContainerSell((TileEntitySell) tileEntity, player.inventory);
            }
            return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,int x, int y, int z) 
    {
        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
    		if(id == GuiIds.GUI_SELL && tileEntity != null)
            {
                    return new GuiSell(player.inventory, (TileEntitySell) tileEntity);
            }
            return null;
    }
}
