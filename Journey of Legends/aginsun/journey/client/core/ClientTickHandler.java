package aginsun.journey.client.core;

import java.awt.Color;
import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import aginsun.journey.api.ExperienceKeeper;
import aginsun.journey.api.GoldKeeper;
import aginsun.journey.api.LevelKeeper;
import aginsun.journey.api.QuestHandler;
import aginsun.journey.client.guis.GuiPriceBar;
import aginsun.journey.client.guis.GuiStartConquest;
import aginsun.journey.core.handlers.packets.PacketSound;
import aginsun.journey.core.handlers.packets.PacketType;
import aginsun.journey.core.questsystem.QuestRegistry;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

public class ClientTickHandler implements ITickHandler
{
	public Minecraft mc;
    public World world;
    private EntityPlayer player;
    public GoldKeeper gold;
    private GuiPriceBar bar;
    private int CreateGuild;
	
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.RENDER)))
        {
            onRenderTick();
        }
        else if (type.equals(EnumSet.of(TickType.CLIENT)))
        {
            GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            if(guiscreen != null)
            {
                onTickInGUI(guiscreen);
            }else{
                onTickInGame();

            }            
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
    }

    @Override
    public String getLabel() { return null; }


    public boolean onRenderTick()
    {
    	mc = FMLClientHandler.instance().getClient();
    	GuiScreen guiscreen = mc.getMinecraft().currentScreen;
    	if((guiscreen == null || (guiscreen instanceof GuiChat)) && !mc.gameSettings.showDebugInfo)
    	{
    		player = FMLClientHandler.instance().getClient().thePlayer;
    		GoldKeeper gold = new GoldKeeper();
    		int x = (255 << 16);
            ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int i = scaledresolution.getScaledWidth() + 8;
            int j = scaledresolution.getScaledHeight();
            
            DrawCube(guiscreen, i, j);
            
            bar = new GuiPriceBar(0, 12, 26, 95, 12, 1.0F, Color.RED.getRGB());
            int worthy = ExperienceKeeper.getExperience(player);
            worthy -= (LevelKeeper.getLevel(player) * 850 - 850);
            bar.setBar(worthy / 850.0F);
            bar.drawBar();
            
            mc.fontRenderer.drawString((new StringBuilder()).append("-Level: ").append(LevelKeeper.getLevel(player)).append("-").toString(), -mc.fontRenderer.getStringWidth("-Level: 200-") / 2 + 63, 28, Color.ORANGE.getRGB());
            mc.fontRenderer.drawString((new StringBuilder()).append("Gold: ").append(gold.getGoldTotal(player)).toString(), 13, 7, x);
            
            Tessellator.instance.setColorOpaque_F(1F, 1F, 1F);
            GL11.glColor4f(1F, 1F, 1F, 1F);
            return true;
    	}
		return false;
    }
    
    public boolean DrawCube(GuiScreen guiscreen, int i, int j)
    {
        byte byte0 = 100;
        byte byte1 = 100;
        byte byte2 = 10;
        int l = j - 34;
        int j1 = 0;
        int k1 = 0;
        float f4 = 0.00390625F;
        float f5 = 0.00390625F;
        float f6 = 0.0F;
        byte byte3 = byte0;
        byte byte4 = byte1;
        guiscreen.drawRect(9, 0, 111, 46, Color.DARK_GRAY.getRGB());
        GL11.glColor4f(0.85F, 0.85F, 0.85F, 0.85F);
        ResourceLocation resource = new ResourceLocation("journeyoflegends", "textures/guis/crafting.png");
        mc.renderEngine.func_110577_a(resource);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(10, 45, f6, (float)(j1 + 10) * f4, (float)(k1 + byte4) * f5);
        tessellator.addVertexWithUV(110, 45, f6, (float)(j1 + byte3) * f4, (float)(k1 + byte4) * f5);
        tessellator.addVertexWithUV(110, 0, f6, (float)(j1 + byte3) * f4, (float)(k1 + 0) * f5);
        tessellator.addVertexWithUV(10, 0, f6, (float)(j1 + 10) * f4, (float)(k1 + 0) * f5);
        tessellator.draw();
        return true;
    }

    public void onTickInGUI(GuiScreen guiscreen){}
    
    boolean displayed;
    
    public void onTickInGame() 
    {
    	if(QuestHandler.instance().getQuestStatusClient(mc.thePlayer, QuestRegistry.getQuest(4).getQuestName()) == 0 && !displayed)
    	{
    		mc.displayGuiScreen(new GuiStartConquest());
    		PacketDispatcher.sendPacketToServer(PacketType.populatePacket(new PacketSound(mc.thePlayer.username, 1.0F, 1.0F)));
    		displayed = true;
    	}
    }
}
