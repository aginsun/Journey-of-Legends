package aginsun.taleofkingdoms.core.handlers;

import java.util.EnumSet;

import aginsun.taleofkingdoms.worldgen.WorldGenGuild;

import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler
{
	private World world;
	int tdd = 0;
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
	   world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
	   if(world != null)
	   {
		   if(tdd == 0)
		   {
			   WorldGenGuild wgg = new WorldGenGuild(world, 10, 20, 30);
			   wgg.CreateGuild();
			   tdd = 1;
			   System.out.println("Done!");
		   }
	   }
   }
}
