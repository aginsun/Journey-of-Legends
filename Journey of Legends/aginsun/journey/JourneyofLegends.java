package aginsun.journey;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;
import aginsun.journey.server.CommonProxy;
import aginsun.journey.server.handlers.CommonTickHandler;
import aginsun.journey.server.handlers.SaveHandler;
import aginsun.journey.universal.ConfigFileJoL;
import aginsun.journey.universal.blocks.InitBlocks;
import aginsun.journey.universal.entities.InitEntities;
import aginsun.journey.universal.handlers.CommandJoLHandler;
import aginsun.journey.universal.handlers.EntityLivingHandler;
import aginsun.journey.universal.handlers.GoldValueHandler;
import aginsun.journey.universal.handlers.LivingAttackEventHandler;
import aginsun.journey.universal.handlers.LivingDeathEventHandler;
import aginsun.journey.universal.handlers.PacketHandler;
import aginsun.journey.universal.handlers.PickupHandler;
import aginsun.journey.universal.handlers.QuestRegistry;
import aginsun.journey.universal.items.InitItems;
import aginsun.journey.universal.utils.Utils;
import aginsun.journey.universal.worldgen.WorldgenChests;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = Utils.modID, version = Utils.Version, name = Utils.Name)
@NetworkMod(channels = { Utils.modID },clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class JourneyofLegends 
{
	@Instance (Utils.modID)
	public static JourneyofLegends instance = new JourneyofLegends();
	
	@SidedProxy(clientSide="aginsun.journey.client.core.ClientProxy",serverSide="aginsun.journey.core.CommonProxy")
	public static CommonProxy proxy;
		
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		ConfigFileJoL.config(event);
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{			
		InitEntities.Init();
		
		InitBlocks.Init();
		
		InitItems.Init();
		
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		
		MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
		
		MinecraftForge.EVENT_BUS.register(new LivingAttackEventHandler());
		
		MinecraftForge.EVENT_BUS.register(new LivingDeathEventHandler());
		
		proxy.RegisterRenderers();
		
		WorldgenChests.Init();
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		proxy.registerPostInit();
		
		GoldValueHandler.setGoldValues();
				
		QuestRegistry.InitQuests();
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{		
		CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandJoLHandler());
	}
	
	@EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{
		GameRegistry.registerPlayerTracker(new SaveHandler());
		GameRegistry.registerPickupHandler(new PickupHandler());
	}
}
