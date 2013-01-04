package aginsun.taleofkingdoms.core.handlers;

import java.util.EnumSet;

import aginsun.taleofkingdoms.entities.EntityGuildMaster;
import aginsun.taleofkingdoms.worldgen.WorldGenGuild;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
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
			   WorldGenGuild wgg = new WorldGenGuild(world, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ(), world.getWorldInfo().getSpawnY());
			   wgg.CreateGuild();
			   tdd = 1;
			   System.out.println("Done!");
		   }
	   }
   }
   
   
   public void spawnGuildMembers()
   {
	   world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
       float f = world.getWorldInfo().getSpawnX() + 0.5F;
       float f1 = world.getWorldInfo().getSpawnY();
       float f2 = world.getWorldInfo().getSpawnZ() + 0.5F;
       
	   EntityLiving entityliving = new EntityGuildMaster(world);
	   
	   EntityList.createEntityByName("GuildMaster", world);
       entityliving.setLocationAndAngles(f + 34F, f1 + 16F, f2 + 66F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving);
	   
   }
}
