package aginsun.journey.items.render;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import aginsun.journey.items.models.ModelClaw;

public class RenderRedClaw implements IItemRenderer
{
	ModelClaw model;
	
	public RenderRedClaw()
	{
		model = new ModelClaw();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) 
	{
		return type == ItemRenderType.EQUIPPED;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		if(type == ItemRenderType.EQUIPPED)
		{
            GL11.glPushMatrix();
            float scale = 1.5F;
            GL11.glScalef(scale, scale, scale);
            Minecraft.getMinecraft().renderEngine.bindTexture("/mods/JourneyOfLegends/textures/items/clawRef.png");
            GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(90F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.2F, 0.0F, 0.0F);
            model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            Minecraft.getMinecraft().renderEngine.resetBoundTexture();
            GL11.glPopMatrix();
		}
	}
}
