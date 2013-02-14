package aginsun.taleofkingdoms.core.handlers;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.HunterKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import aginsun.taleofkingdoms.items.InitItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;

public class mod_GuiTickHandler extends BaseMod
{
	public GoldKeeper gold;
	public EntityPlayer entityplayer;
	public Player par1player;
	public WorthyKeeper worthy;
	public HunterKeeper hunter;
	public StatKeeper stats;
	@Override
	public String getVersion(){return "2.0.0";}

	@Override
	public void load()
	{
		ModLoader.setInGUIHook(this, true, false);
	}
	@SideOnly(Side.CLIENT)
	public boolean onTickInGUI(float f, Minecraft mc, GuiScreen guiscreen)
	{
		entityplayer = FMLClientHandler.instance().getClient().thePlayer;
		GoldKeeper gold = new GoldKeeper();
		int x = (255 << 16);
        if (guiscreen == null)
        {
            ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int i = scaledresolution.getScaledWidth() + 8;
            int j = scaledresolution.getScaledHeight();
            mc.fontRenderer.drawString((new StringBuilder()).append("Level: ").append(stats.getStatPoints(entityplayer, "LVL")).toString(), 0, 0, x);
            mc.fontRenderer.drawString((new StringBuilder()).append("Gold Coins: ").append(gold.getGoldTotal(entityplayer)).toString(), 0, 10, x);
            
        }
        else if(guiscreen instanceof GuiInventory || guiscreen instanceof GuiContainerCreative)
        {
        	mc.fontRenderer.drawString((new StringBuilder()).append("Strength: ").append(stats.getStatPoints(entityplayer, "STR")).toString(), 0, 0, x);
        	mc.fontRenderer.drawString((new StringBuilder()).append("Dexeterity: ").append(stats.getStatPoints(entityplayer, "DEX")).toString(), 0, 15, x);
        	mc.fontRenderer.drawString((new StringBuilder()).append("Intelligence: ").append(stats.getStatPoints(entityplayer, "INT")).toString(), 0, 30, x);
        	mc.fontRenderer.drawString((new StringBuilder()).append("Luck: ").append(stats.getStatPoints(entityplayer, "LUK")).toString(), 0, 45, x);
        	/*ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int i = scaledresolution.getScaledWidth() + 8;
            int j = scaledresolution.getScaledHeight();
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
            guiscreen.drawRect(byte2 - 1, l - 1, byte2 + byte3 + 1, l + byte4 + 1, Color.DARK_GRAY.getRGB());
            int l1 = mc.renderEngine.getTexture("/aginsun/textures/crafting.png");
            GL11.glColor4f(0.85F, 0.85F, 0.85F, 0.85F);
            mc.renderEngine.bindTexture(l1);
            Tessellator tessellator = Tessellator.instance;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(byte2 + 0, l + byte4, f6, (float)(j1 + 10) * f4, (float)(k1 + byte4) * f5);
            tessellator.addVertexWithUV(byte2 + byte3, l + byte4, f6, (float)(j1 + byte3) * f4, (float)(k1 + byte4) * f5);
            tessellator.addVertexWithUV(byte2 + byte3, l + 0, f6, (float)(j1 + byte3) * f4, (float)(k1 + 0) * f5);
            tessellator.addVertexWithUV(byte2 + 0, l + 0, f6, (float)(j1 + 10) * f4, (float)(k1 + 0) * f5);
            tessellator.draw();*/
        }
		return true;	
	}
	
	@Override
    public void onItemPickup(EntityPlayer player, ItemStack itemstack)
    {
        if (itemstack.itemID == InitItems.ItemCoins.shiftedIndex)
        {
            InventoryPlayer inventoryplayer = player.inventory;
            inventoryplayer.consumeInventoryItem(itemstack.itemID);
            gold.addGold(player, 2);
    		this.par1player = (Player)player;
            WorthyKeeper.addWorthy(player);
    		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(player.username, gold.getGoldTotal(player))), par1player);
        }
    }

}
