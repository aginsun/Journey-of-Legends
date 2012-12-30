package aginsun.taleofkingdoms.core.handlers;

import org.lwjgl.util.Color;

import aginsun.taleofkingdoms.core.GoldKeeper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;

public class mod_GuiTickHandler extends BaseMod
{
	public GoldKeeper gold;
	@Override
	public String getVersion(){return null;}

	@Override
	public void load()
	{
		ModLoader.setInGUIHook(this, true, false);
	}
	public boolean onTickInGUI(float f, Minecraft mc, GuiScreen guiscreen)
	{
		GoldKeeper gold = new GoldKeeper();
		int x = (255 << 16);
        if (guiscreen == null)
        {
            ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int i = scaledresolution.getScaledWidth() + 8;
            int j = scaledresolution.getScaledHeight();
            mc.fontRenderer.drawString((new StringBuilder()).append("Gold Coins: ").append(gold.getGoldTotal()).toString(), 0, 0, x);
        }
		return true;	
	}

}
