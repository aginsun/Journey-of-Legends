package aginsun.journey.client.core;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import aginsun.journey.core.CommonProxy;
import aginsun.journey.core.handlers.KeyBindingHandler;
import aginsun.journey.entities.EntityBuilder;
import aginsun.journey.entities.EntityFarmerKeeper;
import aginsun.journey.entities.EntityGuildMaster;
import aginsun.journey.entities.EntityGuildMember;
import aginsun.journey.entities.EntityWeaponKeeper;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy
{
	public void RegisterRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGuildMaster.class, new RenderBiped(new ModelBiped(), 0.4F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RenderBiped(new ModelBiped(), 0.4F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFarmerKeeper.class, new RenderBiped(new ModelBiped(), 0.4F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGuildMember.class, new RenderBiped(new ModelBiped(), 0.4F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWeaponKeeper.class, new RenderBiped(new ModelBiped(), 0.4F));
		
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
		TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
	}

}
