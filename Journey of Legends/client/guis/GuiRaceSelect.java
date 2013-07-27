package aginsun.journey.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import aginsun.journey.core.handlers.RaceKeeper;
import cpw.mods.fml.client.FMLClientHandler;

public class GuiRaceSelect extends GuiScreen
{
	public EntityPlayer player;
	public int buttonSelected;
	public RaceKeeper race;
	
	public GuiRaceSelect()
	{
		this.player = FMLClientHandler.instance().getClient().thePlayer;
	}
	
	public GuiRaceSelect(EntityPlayer player) 
	{
		this.player = player;
	}
	
	public void initGui()
	{
		buttonList.clear();
		
        buttonList.add(new GuiButtonShop(this, 1, width / 2 - 20, 15, 120, 20, "Warrior"));
        buttonList.add(new GuiButtonShop(this, 2, width / 2 - 20, 35, 120, 20, "Hunter"));
        buttonList.add(new GuiButtonShop(this, 3, width / 2 - 20, 55, 120, 20, "Mage"));
        buttonList.add(new GuiButtonShop(this, 4, width / 2 - 20, 75, 120, 20, "Thief"));
        
        if(buttonSelected > 0)
        {
            buttonList.add(new GuiButton(5, width / 2 - 20, 105, 120, 20, "Yes"));
            buttonList.add(new GuiButton(6, width / 2 - 20, 125, 120, 20, "No"));
        }
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
			buttonSelected = 1;
		if(guibutton.id == 2)
			buttonSelected = 2;
		if(guibutton.id == 3)
			buttonSelected = 3;
		if(guibutton.id == 4)
			buttonSelected = 4;
		if(guibutton.id == 5)
		{
			if(buttonSelected == 1)
				race.setClass(player, "Warrior");
			if(buttonSelected == 2)
				race.setClass(player, "Hunter");
			if(buttonSelected == 3)
				race.setClass(player, "Mage");
			if(buttonSelected == 4)
				race.setClass(player, "Thief");
			
			buttonSelected = 0;
			mc.currentScreen = null;
		}
		if(guibutton.id == 6)
			buttonSelected = 0;
		
		initGui();
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
        for (int m = 0; m < buttonList.size(); m++)
        {
            GuiButton guibutton = (GuiButton)buttonList.get(m);
            guibutton.drawButton(mc, i, j);
        }
        super.drawScreen(i, j, f);
	}
}
