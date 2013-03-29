package aginsun.taleofkingdoms.client.core;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import aginsun.taleofkingdoms.core.CommonProxy;
import aginsun.taleofkingdoms.core.handlers.KeyBindingHandler;
import aginsun.taleofkingdoms.entities.EntityBuilder;
import aginsun.taleofkingdoms.entities.EntityFarmerKeeper;
import aginsun.taleofkingdoms.entities.EntityGuildMaster;
import aginsun.taleofkingdoms.entities.EntityGuildMember;
import aginsun.taleofkingdoms.entities.EntityWeaponKeeper;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

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
	}

}
