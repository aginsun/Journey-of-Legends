package aginsun.taleofkingdoms;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.command.CommandHandler;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import aginsun.taleofkingdoms.blocks.InitBlocks;
import aginsun.taleofkingdoms.client.core.ClientTickHandler;
import aginsun.taleofkingdoms.core.CommonProxy;
import aginsun.taleofkingdoms.core.DataStorage;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.handlers.EntityLivingHandler;
import aginsun.taleofkingdoms.core.handlers.PacketHandler;
import aginsun.taleofkingdoms.core.handlers.CommonTickHandler;
import aginsun.taleofkingdoms.core.handlers.mod_GuiTickHandler;
import aginsun.taleofkingdoms.core.handlers.SaveHandlerToK;
import aginsun.taleofkingdoms.core.handlers.commands.CommandTaleofKingdoms;
import aginsun.taleofkingdoms.entities.InitEntities;
import aginsun.taleofkingdoms.items.InitItems;
import aginsun.taleofkingdoms.worldgen.WorldGenGuild;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

@Mod(modid = "TaleOfKingdoms", version = "2.0.0", name = "Tale of Kingdoms 2")
@NetworkMod(channels = { "TaleOfKingdoms" },clientSideRequired = true, serverSideRequired = true, versionBounds = "2.0.0", packetHandler = PacketHandler.class)
public class TaleOfKingdoms 
{
	@Instance ("TaleOfKingdoms")
	public static TaleOfKingdoms instance = new TaleOfKingdoms();
	
	@SidedProxy(clientSide="aginsun.taleofkingdoms.client.core.ClientProxy",serverSide="aginsun.taleofkingdoms.core.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void PreInit(FMLPreInitializationEvent event)
	{
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		
		config.load();
		//TODO Setup File to change it!
		config.save();
	}
	
	@Init
	public void load(FMLInitializationEvent event)
	{
		proxy.Init();
		
		InitEntities.Init();
		
		InitBlocks.Init();
		
		InitItems.Init();
		
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		
		MinecraftForge.EVENT_BUS.register(new EntityLivingHandler());
	}
	
	@PostInit
	public void PostInit(FMLPostInitializationEvent event){}
	
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
