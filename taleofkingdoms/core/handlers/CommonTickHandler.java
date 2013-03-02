package aginsun.taleofkingdoms.core.handlers;

import java.util.EnumSet;

import aginsun.taleofkingdoms.core.ConfigFileToK;
import aginsun.taleofkingdoms.entities.EntityFarmerKeeper;
import aginsun.taleofkingdoms.entities.EntityGuildMaster;
import aginsun.taleofkingdoms.entities.EntityGuildMember;
import aginsun.taleofkingdoms.entities.EntityWeaponKeeper;
import aginsun.taleofkingdoms.worldgen.WorldGenGuild;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.event.FMLServerStartedEvent;

public class CommonTickHandler implements ITickHandler
{
	private World world;
	boolean dataRead;
	public ConfigFileToK x;
	public WorldSaveToKHandler td;
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
		   if(!dataRead)
		   {
			   WorldSaveToKHandler.readData();
			   this.dataRead = true;
		   }
		   
		   if(td.tdd == 0 && !ConfigFileToK.GuildSpawning && dataRead)
		   {
			   if(x.xLocation == 0 && x.yLocation == 0 && x.zLocation == 0)
			   {
				   WorldGenGuild wgg = new WorldGenGuild(world, world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnZ(), world.getWorldInfo().getSpawnY());
				   wgg.CreateGuild();
				   spawnGuildMembers(world.getWorldInfo().getSpawnX(), world.getWorldInfo().getSpawnY(), world.getWorldInfo().getSpawnZ());
			   }else{
				   WorldGenGuild wgg = new WorldGenGuild(world, x.xLocation, x.zLocation, x.yLocation);
				   wgg.CreateGuild();
				   spawnGuildMembers(x.xLocation, x.yLocation, x.zLocation);
			   }
			   td.tdd = 1;
			   WorldSaveToKHandler.writeData();
		   }
	   }
   }   
   
   public void spawnGuildMembers(float f, float f1, float f2)
   {
	   world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
       f += 0.5F;
       f1 += 0.0F;
       f2 += 0.5F;
       
	   EntityLiving entityliving = new EntityGuildMaster(world);
	   EntityLiving entityliving1 = new EntityGuildMember(world);
	   EntityLiving entityliving2 = new EntityGuildMember(world);
	   EntityLiving entityliving3 = new EntityGuildMember(world);
	   EntityLiving entityliving4 = new EntityGuildMember(world);
	   EntityLiving entityliving5 = new EntityGuildMember(world);
	   EntityLiving entityliving6 = new EntityGuildMember(world);
	   EntityLiving entityliving7 = new EntityGuildMember(world);
	   EntityLiving entityliving8 = new EntityGuildMember(world);
	   EntityLiving entityliving9 = new EntityGuildMember(world);
	   EntityLiving entityliving10 = new EntityGuildMember(world);
	   EntityLiving entityliving11 = new EntityGuildMember(world);
	   EntityLiving entityliving12 = new EntityGuildMember(world);
	   EntityLiving entityliving13 = new EntityGuildMember(world);
	   EntityLiving entityliving14 = new EntityGuildMember(world);
	   EntityLiving entityliving15 = new EntityGuildMember(world);
	   EntityLiving entityliving16 = new EntityGuildMember(world);
	   EntityLiving entityliving17 = new EntityGuildMember(world);
	   EntityLiving entityliving18 = new EntityGuildMember(world);
	   EntityLiving entityliving19 = new EntityGuildMember(world);
	   EntityLiving entityliving20 = new EntityGuildMember(world);
	   EntityLiving entityliving21 = new EntityGuildMember(world);
	   EntityLiving entityliving22 = new EntityGuildMember(world);
	   EntityLiving entityliving23 = new EntityGuildMember(world);
	   EntityLiving entityliving24 = new EntityGuildMember(world);
	   EntityLiving entityliving25 = new EntityGuildMember(world);
	   EntityLiving entityliving26 = new EntityGuildMember(world);
	   EntityLiving entityliving27 = new EntityGuildMember(world);
	   EntityLiving entityliving28 = new EntityGuildMember(world);
	   EntityLiving entityliving29 = new EntityGuildMember(world);
	   EntityLiving entityliving30 = new EntityFarmerKeeper(world);
	   EntityLiving entityliving31 = new EntityWeaponKeeper(world);
	   
	   EntityList.createEntityByName("GuildMaster", world);
       entityliving.setLocationAndAngles(f + 34F, f1 + 16F, f2 + 66F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving);
       
		EntityList.createEntityByName("GuildMember", world);
		entityliving1.setLocationAndAngles((double)f + 12.5D, f1 + 11F, f2 + 68F, 0.0F, 0.0F);
		world.spawnEntityInWorld(entityliving1);
		
       EntityList.createEntityByName("GuildMember", world);
       entityliving2.setLocationAndAngles(f + 12F, f1 + 11F, f2 + 40F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving2);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving3.setLocationAndAngles(f + 28F, f1 + 2.0F, f2 + 42F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving3);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving4.setLocationAndAngles(f + 10F, f1 + 11F, f2 + 60F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving4);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving5.setLocationAndAngles(f + 11F, f1 + 18F, f2 + 52F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving5);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving6.setLocationAndAngles(f + 11F, f1 + 18F, f2 + 57F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving6);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving7.setLocationAndAngles(f + 16F, f1 + 11F, f2 + 48F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving7);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving8.setLocationAndAngles(f + 16F, f1 + 18F, f2 + 43F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving8);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving9.setLocationAndAngles(f + 16F, f1 + 18F, f2 + 61F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving9);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving10.setLocationAndAngles(f + 19F, f1 + 11F, f2 + 46F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving10);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving11.setLocationAndAngles(f + 19F, f1 + 11F, f2 + 66F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving11);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving12.setLocationAndAngles(f + 20F, f1 + 10F, f2 + 26F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving12);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving13.setLocationAndAngles(f + 20F, f1 + 11F, f2 + 56F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving13);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving14.setLocationAndAngles(f + 20F, f1 + 18F, f2 + 40F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving14);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving15.setLocationAndAngles(f + 24F, f1 + 8F, f2 + 66F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving15);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving16.setLocationAndAngles(f + 25F, f1 + 10F, f2 + 15F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving16);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving17.setLocationAndAngles(f + 28F, f1 + 8F, f2 + 66F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving17);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving18.setLocationAndAngles(f + 31F, f1 + 2.0F, f2 + 32F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving18);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving19.setLocationAndAngles(f + 32F, f1 + 9F, f2 + 38F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving19);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving20.setLocationAndAngles(f + 34F, f1 + 8F, f2 + 66F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving20);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving21.setLocationAndAngles(f + 41F, f1 + 9F, f2 + 41F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving21);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving22.setLocationAndAngles(f + 41F, f1 + 9F, f2 + 59F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving22);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving23.setLocationAndAngles(f + 42F, f1 + 2.0F, f2 + 23F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving23);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving24.setLocationAndAngles(f + 43F, f1 + 10F, f2 + 13F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving24);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving25.setLocationAndAngles(f + 50F, f1 + 2.0F, f2 + 36F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving25);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving26.setLocationAndAngles(f + 50F, f1 + 3F, f2 + 54F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving26);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving27.setLocationAndAngles(f + 57F, f1 + 12F, f2 + 39F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving27);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving28.setLocationAndAngles(f + 70F, f1 + 17F, f2 + 48F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving28);
       
       EntityList.createEntityByName("GuildMember", world);
       entityliving29.setLocationAndAngles(f + 70F, f1 + 25F, f2 + 45F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving29);
       					
       EntityList.createEntityByName("Farmer", world);
       entityliving30.setLocationAndAngles(f + 54F, f1 + 2.0F, f2 + 50F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving30);
       
       EntityList.createEntityByName("WeaponKeeper", world);
       entityliving31.setLocationAndAngles(f + 49F, f1 + 3F, f2 + 25F, 0.0F, 0.0F);
       world.spawnEntityInWorld(entityliving31);
   }
}
