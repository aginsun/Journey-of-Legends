package aginsun.taleofkingdoms.client.guis;

import java.awt.Color;

import aginsun.taleofkingdoms.api.QuestHandler;
import aginsun.taleofkingdoms.core.quests.Quest;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiQuest extends GuiScreen
{
	private String QuestName;
	private Quest quest;
	private int number;
	
	public GuiQuest(String QuestName, Quest quest, int number)
	{
		this.QuestName = QuestName;
		this.quest = quest;
		this.number = number;
	}
	
	public void initGui()
	{
		buttonList.clear();
		buttonList.add(new GuiButton(1, width / 2, height / 2, 120, 20, "Yes"));
		buttonList.add(new GuiButton(2, width / 2, height / 2 + 20, 120, 20, "No"));
		buttonList.add(new GuiButton(3, width / 2, height / 2 + 40, 120, 20, "Finish Quest!"));
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			QuestHandler.setQuestStarted(mc.thePlayer, QuestName);
			mc.thePlayer.addChatMessage(quest.QuestStartLines(number));
			mc.currentScreen = null;
		}
		if(guibutton.id == 2)
			mc.currentScreen = null;
		
		if(guibutton.id == 3)
		{
			if(QuestHandler.getQuestStatus(mc.thePlayer, QuestName) == 2)
			{
				quest.QuestEndReward(mc.thePlayer, number);
				QuestHandler.questFinishedReward(mc.thePlayer, QuestName);
			}
			mc.currentScreen = null;
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public void drawScreen(int i, int k, float f)
	{
		drawString(fontRenderer, "Quest Name: " + QuestName, width / 2, 10, Color.PINK.getRGB());
		super.drawScreen(i, k, f);
	}
}
