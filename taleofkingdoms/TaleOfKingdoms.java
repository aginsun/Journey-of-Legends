package aginsun.taleofkingdoms;

import net.minecraft.block.Block;
import net.minecraft.command.CommandHandler;
import net.minecraftforge.common.MinecraftForge;
import aginsun.taleofkingdoms.blocks.InitBlocks;
import aginsun.taleofkingdoms.client.core.ClientTickHandler;
import aginsun.taleofkingdoms.core.CommonProxy;
import aginsun.taleofkingdoms.core.ConfigFileToK;
import aginsun.taleofkingdoms.core.handlers.CommonTickHandler;
import aginsun.taleofkingdoms.core.handlers.EntityLivingHandler;
import aginsun.taleofkingdoms.core.handlers.GoldValues;
import aginsun.taleofkingdoms.core.handlers.LivingAttackEventHandler;
import aginsun.taleofkingdoms.core.handlers.PacketHandler;
import aginsun.taleofkingdoms.core.handlers.PickupHandler;
import aginsun.taleofkingdoms.core.handlers.SaveHandlerToK;
import aginsun.taleofkingdoms.core.handlers.commands.CommandTaleofKingdoms;
import aginsun.taleofkingdoms.core.quests.QuestRegistry;
import aginsun.taleofkingdoms.entities.InitEntities;
import aginsun.taleofkingdoms.items.InitItems;
import aginsun.taleofkingdoms.kingdom.BuildingRegistry;
import aginsun.taleofkingdoms.util.Utils;
import aginsun.taleofkingdoms.worldgen.WorldgenChests;
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
public class TaleOfKingdoms 
{
	@Instance (Utils.modID)
	public static TaleOfKingdoms instance = new TaleOfKingdoms();
	
	@SidedProxy(clientSide="aginsun.taleofkingdoms.client.core.ClientProxy",serverSide="aginsun.taleofkingdoms.core.CommonProxy")
	public static CommonProxy proxy;
		
	@PreInit
	public void PreInit(FMLPreInitializationEvent event)
	{
		ConfigFileToK.config(event);
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
		
		MinecraftForge.EVENT_BUS.register(new PickupHandler());
		
		WorldgenChests.Init();
	}
	
	@PostInit
	public void PostInit(FMLPostInitializationEvent event)
	{
		GoldValues.setGoldValues();
		
		BuildingRegistry.RegisterBuildings();
		
		QuestRegistry.InitQuests();
	}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event)
	{		
		CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandTaleofKingdoms());
	}
	
	@ServerStarted
	public void serverStarted(FMLServerStartedEvent event)
	{
		GameRegistry.registerPlayerTracker(new SaveHandlerToK());
	}
}
