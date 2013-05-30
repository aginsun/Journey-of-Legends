package aginsun.journey;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;
import aginsun.journey.blocks.InitBlocks;
import aginsun.journey.core.CommonProxy;
import aginsun.journey.core.ConfigFileJoL;
import aginsun.journey.core.handlers.CommonTickHandler;
import aginsun.journey.core.handlers.EntityLivingHandler;
import aginsun.journey.core.handlers.GoldValues;
import aginsun.journey.core.handlers.LivingAttackEventHandler;
import aginsun.journey.core.handlers.LivingDeathEventHandler;
import aginsun.journey.core.handlers.PacketHandler;
import aginsun.journey.core.handlers.PickupHandler;
import aginsun.journey.core.handlers.SaveHandlerToK;
import aginsun.journey.core.handlers.commands.CommandJourneyofLegends;
import aginsun.journey.core.quests.QuestRegistry;
import aginsun.journey.entities.InitEntities;
import aginsun.journey.items.InitItems;
import aginsun.journey.util.Utils;
import aginsun.journey.worldgen.WorldgenChests;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStarting;
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
		
	@PreInit
	public void PreInit(FMLPreInitializationEvent event)
	{
		ConfigFileJoL.config(event);
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.RegisterRenderers();
				
		InitEntities.Init();
		
		InitBlocks.Init();
		
		InitItems.Init();
		
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		
		MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
		
		MinecraftForge.EVENT_BUS.register(new LivingAttackEventHandler());
		
		MinecraftForge.EVENT_BUS.register(new LivingDeathEventHandler());
		
		WorldgenChests.Init();
	}
	
	@PostInit
	public void PostInit(FMLPostInitializationEvent event)
	{
		GoldValues.setGoldValues();
				
		QuestRegistry.InitQuests();
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{		
		CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandJourneyofLegends());
	}
	
	@ServerStarted
	public void serverStarted(FMLServerStartedEvent event)
	{
		GameRegistry.registerPlayerTracker(new SaveHandlerToK());
		GameRegistry.registerPickupHandler(new PickupHandler());
	}
}
