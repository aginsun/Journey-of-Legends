package aginsun.taleofkingdoms.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import aginsun.taleofkingdoms.core.goldSystem.LevelKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import cpw.mods.fml.client.FMLClientHandler;

public class GuiStats extends GuiScreen
{
	private StatKeeper stats;
	private EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
	private LevelKeeper level;
	
	public void initGui()
	{
        buttonList.clear();

		if(level.getLevelUps(player) > 0)
		{
			buttonList.add(new GuiButton(1, width / 2 - 20, 15, 120, 20, "Upgrade Strength"));
			buttonList.add(new GuiButton(2, width / 2 - 20, 75, 120, 20, "Upgrade Dexerity"));
			buttonList.add(new GuiButton(3, width / 2 - 20, 135, 120, 20, "Upgrade Intelligence"));
			buttonList.add(new GuiButton(4, width / 2 - 20, 195, 120, 20, "Upgrade Luck"));
		}
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			level.decreaseLevelPoints(player);
			stats.addStrengthPoints(player, 1);
			if(level.getLevelPoints(player) == 0)
			{
				level.decreaseLevelUps(player);
			}
			
			initGui();
		}
		if(guibutton.id == 2)
		{
			level.decreaseLevelPoints(player);
			stats.addDexPoints(player, 1);
			if(level.getLevelPoints(player) == 0)
			{
				level.decreaseLevelUps(player);
			}
			
			initGui();
		}
		if(guibutton.id == 3)
		{
			level.decreaseLevelPoints(player);
			stats.addIntPoints(player, 1);
			if(level.getLevelPoints(player) == 0)
			{
				level.decreaseLevelUps(player);
			}
			
			initGui();
		}
		if(guibutton.id == 4)
		{
			level.decreaseLevelPoints(player);
			stats.addLukPoints(player, 1);
			if(level.getLevelPoints(player) == 0)
			{
				level.decreaseLevelUps(player);
			}
			
			initGui();
		}
	}
	
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    public void drawScreen(int i, int j, float f)
	{
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	char c = '\377';
    	char c1 = '\377';
    	mc.renderEngine.bindTexture("/aginsun/textures/crafting.png");
    	int i1 = (width - c) / 2;
    	drawTexturedModalRect(i1, 0, 0, 0, c, c1);
    	drawString(fontRenderer, new StringBuilder("Strength: ").append(stats.getStrengthPoints(player)).toString(), width / 2 - 100, 20, 0xffcc00);
    	drawString(fontRenderer, new StringBuilder("Dexerity: ").append(stats.getDexerityPoints(player)).toString(), width / 2 - 100, 80, 0xffcc00);
    	drawString(fontRenderer, new StringBuilder("Intelligence: ").append(stats.getIntelligencePoints(player)).toString(), width / 2 - 100, 140, 0xffcc00);
    	drawString(fontRenderer, new StringBuilder("Luck: ").append(stats.getLuckPoints(player)).toString(), width / 2 - 100, 200, 0xffcc00);

        for (int m = 0; m < buttonList.size(); m++)
        {
            GuiButton guibutton = (GuiButton)buttonList.get(m);
            guibutton.drawButton(mc, i, j);
        }
        super.drawScreen(i, j, f);
	}
}
