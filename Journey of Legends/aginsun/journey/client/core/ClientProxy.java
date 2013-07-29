package aginsun.journey.client.core;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import aginsun.journey.core.CommonProxy;
import aginsun.journey.core.handlers.KeyBindingHandler;
import aginsun.journey.entities.EntityBuilder;
import aginsun.journey.entities.EntityFarmerKeeper;
import aginsun.journey.entities.EntityGuildMaster;
import aginsun.journey.entities.EntityGuildMember;
import aginsun.journey.entities.EntityShuricken;
import aginsun.journey.entities.EntityWeaponKeeper;
import aginsun.journey.entities.render.RenderBipedJoL;
import aginsun.journey.events.JourneySoundLoadEvent;
import aginsun.journey.items.InitItems;
import aginsun.journey.items.render.RenderRedClaw;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
	public void RegisterRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGuildMaster.class, new RenderBipedJoL(new ModelBiped(), 0.4F, "textures/entities/guildmaster.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RenderBipedJoL(new ModelBiped(), 0.4F, "textures/entities/builder.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityFarmerKeeper.class, new RenderBipedJoL(new ModelBiped(), 0.4F, "textures/entities/farmer.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityGuildMember.class, new RenderBipedJoL(new ModelBiped(), 0.4F, "textures/entities/guildmember.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityWeaponKeeper.class, new RenderBipedJoL(new ModelBiped(), 0.4F, "textures/entities/weaponkeeper.png"));
		RenderingRegistry.registerEntityRenderingHandler(EntityShuricken.class, new RenderSnowball(InitItems.itemShuricken));
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
		MinecraftForge.EVENT_BUS.register(new JourneySoundLoadEvent());
	}
	
	@Override
	public void registerPostInit()
	{
		MinecraftForgeClient.registerItemRenderer(InitItems.itemRedClaw.itemID, new RenderRedClaw());
	}
}
