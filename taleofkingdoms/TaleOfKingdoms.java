package aginsun.taleofkingdoms;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import aginsun.taleofkingdoms.blocks.InitBlocks;
import aginsun.taleofkingdoms.client.core.ClientPacketHandler;
import aginsun.taleofkingdoms.client.core.ClientTickHandler;
import aginsun.taleofkingdoms.core.CommonProxy;
import aginsun.taleofkingdoms.core.DataStorage;
import aginsun.taleofkingdoms.core.GoldKeeper;
import aginsun.taleofkingdoms.core.handlers.CommonPacketHandler;
import aginsun.taleofkingdoms.core.handlers.CommonTickHandler;
import aginsun.taleofkingdoms.core.handlers.mod_GuiTickHandler;
import aginsun.taleofkingdoms.core.handlers.SaveHandlerToK;
import aginsun.taleofkingdoms.entities.InitEntities;
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

@Mod(modid = "TaleOfKingdoms", version = "2.0.0 Alpha", name = "Tale of Kingdoms 2")
@NetworkMod(clientSideRequired = true, serverSideRequired = false,
clientPacketHandlerSpec =
@SidedPacketHandler(channels = {"TaleOfKingdoms" }, packetHandler = ClientPacketHandler.class),
serverPacketHandlerSpec =
@SidedPacketHandler(channels = {"TaleOfKingdoms" }, packetHandler = CommonPacketHandler.class))
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
		
        NetworkRegistry.instance().registerGuiHandler(instance, proxy);
        
		TickRegistry.registerTickHandler(new CommonTickHandler(), Side.SERVER);
		
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}
	
	@PostInit
	public void PostInit(FMLPostInitializationEvent event){}
	
	@ServerStarting
	public void serverStarting(FMLServerStartingEvent event){/*TODO:add main commands to protect from griefing + starting new world*/}
	
	@ServerStarted
	public void serverStarted(FMLServerStartedEvent event)
	{
		GameRegistry.registerPlayerTracker(new DataStorage());
	}
}
