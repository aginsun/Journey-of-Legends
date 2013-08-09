package aginsun.journey.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import aginsun.journey.universal.utils.Quest;

public class GuiQuestProgress extends GuiScreen 
{
	private Quest quest;
	
	public GuiQuestProgress(Quest quest) 
	{
		this.quest = quest;
	}
	
	@Override
	public void initGui()
	{
		
	}
	
	@Override
	public void actionPerformed(GuiButton guibutton)
	{
		
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void drawScreen(int i, int j, float f)
	{
		//QUEST STUFF :D
		//MORE STUFF TO BREAK! TODO: Break SHIT
	}
}
