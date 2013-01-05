package aginsun.taleofkingdoms.client.core;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import aginsun.taleofkingdoms.core.CommonProxy;
import aginsun.taleofkingdoms.entities.EntityBuilder;
import aginsun.taleofkingdoms.entities.EntityGuildMaster;

public class ClientProxy extends CommonProxy
{
	public void RegisterRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityGuildMaster.class, new RenderBiped(new ModelBiped(), 0.4F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBuilder.class, new RenderBiped(new ModelBiped(), 0.4F));
	}

}
