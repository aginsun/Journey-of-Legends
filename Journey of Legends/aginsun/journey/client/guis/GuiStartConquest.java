package aginsun.journey.client.guis;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import aginsun.journey.universal.handlers.QuestRegistry;
import aginsun.journey.universal.packets.PacketQuestData;
import aginsun.journey.universal.packets.PacketType;
import aginsun.journey.universal.utils.Quest;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiStartConquest extends GuiScreen
{
	private Quest quest = QuestRegistry.getQuest(4);
	public void initGui()
	{
		buttonList.clear();
		buttonList.add(new GuiButton(1, width / 2 - 60, height / 2 , 120, 20, "Start Your Journey"));
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			quest.setPlayer(mc.thePlayer);
			PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketQuestData(mc.thePlayer.username, quest.getQuestName(), 1)));
			mc.currentScreen = null;
		}
	}
	
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	public void drawScreen(int i, int j, float f)
	{
		drawDefaultBackground();
		String[] string = {"This is the start of a great adventure,","You are going on a journey to become a legend","The legend of Minecraft.","Now go, and find the guildmaster!"};
		drawString(fontRenderer, string[0], width / 2 - mc.fontRenderer.getStringWidth(string[0]) / 2, (height / 2 - 40) + (0 * 10), Color.red.getRGB());
		drawString(fontRenderer, string[1], width / 2 - mc.fontRenderer.getStringWidth(string[1]) / 2, (height / 2 - 40) + (1 * 10), Color.red.getRGB());
		drawString(fontRenderer, string[2], width / 2 - mc.fontRenderer.getStringWidth(string[2]) / 2, (height / 2 - 40) + (2 * 10), Color.red.getRGB());
		drawString(fontRenderer, string[3], width / 2 - mc.fontRenderer.getStringWidth(string[3]) / 2, (height / 2 - 40) + (3 * 10), Color.red.getRGB());
		
		super.drawScreen(i, j, f);
	}
}
