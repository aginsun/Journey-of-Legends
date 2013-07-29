package aginsun.journey.client.guis;


import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import aginsun.journey.api.QuestHandler;
import aginsun.journey.core.questsystem.Quest;
import aginsun.journey.core.questsystem.QuestRegistry;



public class GuiQuestActive extends GuiScreen
{
	private EntityPlayer player;
	private int page;
	
	ArrayList<Quest> questList;
	
	public GuiQuestActive(EntityPlayer player)
	{
		this.player = player;
		page = 0;
	}
	
	public void initGui()
	{
		buttonList.clear();
		questList = getAllQuestsActive();
		
		if(questList.size() > 16)
		{
			buttonList.add(new GuiButton(1, width / 2 - 80, height / 4 * 3, 60, 20, "Next"));
			buttonList.add(new GuiButton(2, width / 2 + 20, height / 4 * 3, 60, 20, "Previous"));
		}
		
		for(int i = 0; i < questList.size(); i++)
		{
			int witht;
			Quest quest = questList.get(page * 16 + i);
			if(i <= 8)
				witht = width / 2 - 130;
			else
				witht = width / 2 + 10;
			buttonList.add(new GuiButton(3 + i, witht, height - height + 10 + (i * 25), 120, 20, quest.getQuestName()));
		}
 	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
			page++;
		if(guibutton.id == 2)
			page--;
		for(Quest quest : questList)
		{
			if(guibutton.displayString.equals(quest.getQuestName()))
			{
				mc.displayGuiScreen(new GuiQuestProgress(quest));
			}
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public ArrayList getAllQuestsActive()
	{
		ArrayList<Quest> list = new ArrayList<Quest>();
		for (Quest quest : QuestRegistry.getMap().values())
		{
			if(QuestHandler.instance().isQuestActiveClient(player, quest.getQuestName()))
			{
				list.add(quest);
			}
		}
		return list;
	}
}
