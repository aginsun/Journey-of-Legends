package aginsun.journey.client.guis;

import java.awt.Color;

import aginsun.journey.api.QuestHandler;
import aginsun.journey.core.quests.Quest;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiQuest extends GuiScreen
{
	private String QuestName;
	private Quest quest;
	private boolean enabled;
	
	public GuiQuest(String QuestName, Quest quest, boolean CanFinishQuest)
	{
		this.QuestName = QuestName;
		this.quest = quest;
		this.enabled = CanFinishQuest;
	}
	
	public void initGui()
	{
		buttonList.clear();
		if(!enabled)
		{
			buttonList.add(new GuiButton(1, width / 2 - 120, height / 2, 80, 20, "Yes"));
			buttonList.add(new GuiButton(2, width / 2 + 40, height / 2, 80, 20, "No"));
		}
		else
			buttonList.add(new GuiButton(3, width / 2 - 60, height / 2, 120, 20, "Finish Quest!"));
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			QuestHandler.instance().setQuestStarted(mc.thePlayer, QuestName);
			mc.thePlayer.addChatMessage(quest.questStartLines(mc.thePlayer));
			mc.currentScreen = null;
		}
		if(guibutton.id == 2)
			mc.currentScreen = null;
		
		if(guibutton.id == 3)
		{
			quest.questEndReward(mc.thePlayer);
			if(quest.questEndLines(mc.thePlayer).contains("-"))
			{
				String[] list = quest.questEndLines(mc.thePlayer).split("-");
				for(int x = 0; x < list.length; x++)	
					{mc.thePlayer.addChatMessage(list[x]);}
				QuestHandler.instance().questFinishedReward(mc.thePlayer, QuestName);
				mc.currentScreen = null;
			}
			else
			{
				mc.thePlayer.addChatMessage(quest.questEndLines(mc.thePlayer));
				QuestHandler.instance().questFinishedReward(mc.thePlayer, QuestName);
				mc.currentScreen = null;
			}
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public void drawScreen(int i, int k, float f)
	{
		drawString(fontRenderer, "Quest Name:", width / 2, height / 2 - 20, Color.PINK.getRGB());
		drawString(fontRenderer, QuestName, width / 2, height / 2, Color.pink.getRGB());
		super.drawScreen(i, k, f);
	}
}
