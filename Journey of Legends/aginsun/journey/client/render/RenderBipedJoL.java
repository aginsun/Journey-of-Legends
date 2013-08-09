package aginsun.journey.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBipedJoL extends RenderBiped
{
	private String location;
	
	public RenderBipedJoL(ModelBiped par1ModelBiped, float par2, String location) 
	{
		super(par1ModelBiped, par2);
		this.location = location;
	}
	
	@Override
	protected ResourceLocation func_110775_a(Entity entity) 
	{
		return (new ResourceLocation("journeyoflegends", location));
	}
	
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRender(par1Entity, par2, par4, par6, par8, par9);
    }
}
