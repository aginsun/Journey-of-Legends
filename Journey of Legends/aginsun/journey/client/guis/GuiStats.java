package aginsun.journey.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import aginsun.journey.server.api.LevelKeeper;
import aginsun.journey.server.api.QuestHandler;
import aginsun.journey.server.api.StatKeeper;
import aginsun.journey.universal.packets.PacketQuestData;
import aginsun.journey.universal.packets.PacketStatChange;
import aginsun.journey.universal.packets.PacketType;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiStats extends GuiScreen
{
	private StatKeeper stats;
	private EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
	private LevelKeeper level;
	
	public void initGui()
	{
        buttonList.clear();

		if(level.getSP(player) > 0)
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
			PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketStatChange(player.username, "STR", 1)));
			initGui();
		}
		if(guibutton.id == 2)
		{
			PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketStatChange(player.username, "DEX", 1)));
			initGui();
		}
		if(guibutton.id == 3)
		{
			PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketStatChange(player.username, "INT", 1)));
			initGui();
		}
		if(guibutton.id == 4)
		{
			PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketStatChange(player.username, "LUK", 1)));
			initGui();
		}
	}
	
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    public void onGuiClosed() 
    {
    	if(QuestHandler.instance().getQuestStatusClient(player, "The beginning of a great adventure") == 1)
    		PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketQuestData(player.username, "The beginning of a great adventure", 2)));
    }

    
    public void drawScreen(int i, int j, float f)
	{
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	char c = '\377';
    	char c1 = '\377';
        ResourceLocation resource = new ResourceLocation("journeyoflegends", "textures/guis/crafting.png");
        mc.renderEngine.func_110577_a(resource);
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
