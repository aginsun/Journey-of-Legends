package aginsun.journey.client.guis;

import java.util.Map;

import aginsun.journey.api.QuestHandler;
import aginsun.journey.core.quests.Quest;
import aginsun.journey.core.quests.QuestRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

public class GuiQuestActive extends GuiScreen
{
	private Quest quest;
	private EntityPlayer player;
	
	public GuiQuestActive(EntityPlayer player)
	{
		this.player = player;
	}
	
	public void initGui()
	{
		buttonList.clear();
		
		buttonList.add(new GuiButton(1, width / 2, height / 2, 120, 20, "123"));
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			for(int i = 1; i <= QuestRegistry.getMap().size(); i++)
			{
				quest = QuestRegistry.getQuest(i).setPlayer(player);
				if(QuestHandler.instance().isQuestActive(player, quest.getQuestName()))
					System.out.println(quest.getQuestName() + "is active!");
			}
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
