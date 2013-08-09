package aginsun.journey.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import aginsun.journey.client.handlers.ClientTickHandler;
import aginsun.journey.client.handlers.KeyBindingHandler;
import aginsun.journey.client.render.RenderBipedJoL;
import aginsun.journey.server.CommonProxy;
import aginsun.journey.universal.entities.EntityBuilder;
import aginsun.journey.universal.entities.EntityFarmerKeeper;
import aginsun.journey.universal.entities.EntityGuildMaster;
import aginsun.journey.universal.entities.EntityGuildMember;
import aginsun.journey.universal.entities.EntityShuricken;
import aginsun.journey.universal.entities.EntityWeaponKeeper;
import aginsun.journey.universal.items.InitItems;
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
	}
	
	@Override
	public void registerPostInit()
	{
	}
}
