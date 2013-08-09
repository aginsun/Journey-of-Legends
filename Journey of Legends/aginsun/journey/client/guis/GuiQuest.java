package aginsun.journey.client.guis;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import aginsun.journey.universal.packets.PacketQuestData;
import aginsun.journey.universal.packets.PacketType;
import aginsun.journey.universal.utils.Quest;
import cpw.mods.fml.common.network.PacketDispatcher;

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
			PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketQuestData(mc.thePlayer.username, quest.getQuestName(), 1)));
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
				PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketQuestData(mc.thePlayer.username, quest.getQuestName(), 3)));
				mc.currentScreen = null;
			}
			else
			{
				mc.thePlayer.addChatMessage(quest.questEndLines(mc.thePlayer));
				PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketQuestData(mc.thePlayer.username, quest.getQuestName(), 3)));
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
		drawDefaultBackground();
		drawString(fontRenderer, "Quest Name: " + quest.getQuestName(), width / 2 - mc.fontRenderer.getStringWidth("Quest Name: " + quest.getQuestName()) / 2, height / 2 - 20, Color.RED.getRGB());
		drawString(fontRenderer, "Quest Discription", width / 2 - mc.fontRenderer.getStringWidth("Quest Discription") / 2, height / 2 + 30, Color.MAGENTA.getRGB());
		System.out.println(quest.getQuestName());
		String[] questString = quest.getQuestDiscription();
		if(questString.length >= 1)
		{
			drawString(fontRenderer, questString[0], width / 2 - mc.fontRenderer.getStringWidth(questString[0]) / 2, height / 2 + 40, Color.RED.getRGB());
			if(questString.length >= 2)
			{
				drawString(fontRenderer, questString[1], width / 2 - mc.fontRenderer.getStringWidth(questString[1]) / 2, height / 2 + 50, Color.RED.getRGB());
				if(questString.length >= 3)
					drawString(fontRenderer, questString[2], width / 2 - mc.fontRenderer.getStringWidth(questString[2]) / 2, height / 2 + 60, Color.RED.getRGB());
			}

		}
		super.drawScreen(i, k, f);
	}
}
