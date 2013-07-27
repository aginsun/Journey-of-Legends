package aginsun.journey.core.handlers;

import java.util.EnumSet;

import net.minecraft.world.World;
import aginsun.journey.worldgen.WorldGenStart;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler
{
	public boolean buildingsCreated;
	private World world;
	private boolean dataRead;
	public WorldSaveToKHandler saveHandler;

	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if(type.equals(EnumSet.of(TickType.SERVER)))
		{
			onTickInGame();
		}
	}

    public EnumSet ticks()
	{
    	return EnumSet.of(TickType.SERVER);
	}

   public String getLabel()
   {
	   return null;
   }
	    
   public void onTickInGame()
   {
	   if(!dataRead)
	   {
		   saveHandler.readData();
		   dataRead = true;
	   }
	   if(!buildingsCreated && dataRead)
	   {
		   world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
		   WorldGenStart worldgen = new WorldGenStart(world, world.getSpawnPoint().posX, world.getSpawnPoint().posY, world.getSpawnPoint().posZ);
		   buildingsCreated = worldgen.createPart1();
		   saveHandler.writeData();
		   buildingsCreated = true;
	   }
   }
}
