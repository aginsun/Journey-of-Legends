package aginsun.taleofkingdoms.core.handlers;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import aginsun.taleofkingdoms.client.guis.GuiRaceSelect;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.HunterKeeper;
import aginsun.taleofkingdoms.core.goldSystem.LevelKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import aginsun.taleofkingdoms.items.InitItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiChat;
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
	public LevelKeeper level;
	public LevelXPToK x;
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
		if (guiscreen == null || guiscreen instanceof GuiChat)
        {
            ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
            int i = scaledresolution.getScaledWidth() + 8;
            int j = scaledresolution.getScaledHeight();
            mc.fontRenderer.drawString((new StringBuilder()).append("Level: ").append(stats.getLevel(entityplayer)).toString(), 0, 0, x);
            mc.fontRenderer.drawString((new StringBuilder()).append("Gold Coins: ").append(gold.getGoldTotal(entityplayer)).toString(), 0, 10, x);
            Tessellator.instance.setColorOpaque_F(1F, 1F, 1F);
            GL11.glColor4f(1F, 1F, 1F, 1F);
        }
		return true;	
	}
	
	@Override
    public void onItemPickup(EntityPlayer player, ItemStack itemstack)
    {
        if (itemstack.itemID == InitItems.ItemCoins.itemID)
        {
            InventoryPlayer inventoryplayer = player.inventory;
            inventoryplayer.consumeInventoryItem(itemstack.itemID);
            gold.addGold(player);
    		this.par1player = (Player)player;
            worthy.addWorthy(player);
    		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(player.username, gold.getGoldTotal(player))), par1player);
    		setLevel(player);
    		checkLevelUps(player);
        }
    }
	
	public void setLevel(EntityPlayer player)
	{
		if(worthy.getWorthy(player) >= x.Level2 && worthy.getWorthy(player) < x.Level3)
			stats.setLevel(player, 2);
		if(worthy.getWorthy(player) >= x.Level3 && worthy.getWorthy(player) < x.Level4)
			stats.setLevel(player, 3);
		if(worthy.getWorthy(player) >= x.Level4 && worthy.getWorthy(player) < x.Level5)
			stats.setLevel(player, 4);
		if(worthy.getWorthy(player) >= x.Level5 && worthy.getWorthy(player) < x.Level6)
			stats.setLevel(player, 5);
		if(worthy.getWorthy(player) >= x.Level6 && worthy.getWorthy(player) < x.Level7)
			stats.setLevel(player, 6);
		if(worthy.getWorthy(player) >= x.Level7 && worthy.getWorthy(player) < x.Level8)
			stats.setLevel(player, 7);
		if(worthy.getWorthy(player) >= x.Level8 && worthy.getWorthy(player) < x.Level9)
			stats.setLevel(player, 8);
		if(worthy.getWorthy(player) >= x.Level9 && worthy.getWorthy(player) < x.Level10)
			stats.setLevel(player, 9);
		if(worthy.getWorthy(player) >= x.Level10 && worthy.getWorthy(player) < x.Level11)
			stats.setLevel(player, 10);
		if(worthy.getWorthy(player) >= x.Level11 && worthy.getWorthy(player) < x.Level12)
			stats.setLevel(player, 11);
		if(worthy.getWorthy(player) >= x.Level12 && worthy.getWorthy(player) < x.Level13)
			stats.setLevel(player, 12);
		if(worthy.getWorthy(player) >= x.Level13 && worthy.getWorthy(player) < x.Level14)
			stats.setLevel(player, 13);
		if(worthy.getWorthy(player) >= x.Level14 && worthy.getWorthy(player) < x.Level15)
			stats.setLevel(player, 14);
		if(worthy.getWorthy(player) >= x.Level15 && worthy.getWorthy(player) < x.Level16)
			stats.setLevel(player, 15);
		if(worthy.getWorthy(player) >= x.Level16 && worthy.getWorthy(player) < x.Level17)
			stats.setLevel(player, 16);
		if(worthy.getWorthy(player) >= x.Level17 && worthy.getWorthy(player) < x.Level18)
			stats.setLevel(player, 17);
		if(worthy.getWorthy(player) >= x.Level18 && worthy.getWorthy(player) < x.Level19)
			stats.setLevel(player, 18);
		if(worthy.getWorthy(player) >= x.Level19 && worthy.getWorthy(player) < x.Level20)
			stats.setLevel(player, 19);
		if(worthy.getWorthy(player) >= x.Level20 && worthy.getWorthy(player) < x.Level21)
			stats.setLevel(player, 20);
		if(worthy.getWorthy(player) >= x.Level21 && worthy.getWorthy(player) < x.Level22)
			stats.setLevel(player, 21);
		if(worthy.getWorthy(player) >= x.Level22 && worthy.getWorthy(player) < x.Level23)
			stats.setLevel(player, 22);
		if(worthy.getWorthy(player) >= x.Level23 && worthy.getWorthy(player) < x.Level24)
			stats.setLevel(player, 23);
		if(worthy.getWorthy(player) >= x.Level24 && worthy.getWorthy(player) < x.Level25)
			stats.setLevel(player, 24);
		if(worthy.getWorthy(player) >= x.Level25 && worthy.getWorthy(player) < x.Level26)
			stats.setLevel(player, 25);
		if(worthy.getWorthy(player) >= x.Level26 && worthy.getWorthy(player) < x.Level27)
			stats.setLevel(player, 26);
		if(worthy.getWorthy(player) >= x.Level27 && worthy.getWorthy(player) < x.Level28)
			stats.setLevel(player, 27);
		if(worthy.getWorthy(player) >= x.Level28 && worthy.getWorthy(player) < x.Level29)
			stats.setLevel(player, 28);
		if(worthy.getWorthy(player) >= x.Level29 && worthy.getWorthy(player) < x.Level30)
			stats.setLevel(player, 29);
		if(worthy.getWorthy(player) >= x.Level30 && worthy.getWorthy(player) < x.Level31)
			stats.setLevel(player, 30);


	}
	
	public void checkLevelUps(EntityPlayer player)
	{
		if(level.getCurrentLevel(player) != stats.getLevel(player))
		{
			level.addLevelUps(player, 1);
			level.addLevelPoints(player, 5);
			if(stats.getLevel(player) == 10 && level.getCurrentLevel(player) == 9)
			{
				//TODO: open up gui, with class choice!
				FMLCommonHandler.instance().showGuiScreen(new GuiRaceSelect(player));
			}
			level.setCurrentLevel(player, stats.getLevel(player));
		}
	}
}
