package aginsun.taleofkingdoms.core.handlers;

import org.lwjgl.util.Color;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.HunterKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import aginsun.taleofkingdoms.items.InitItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
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
            mc.fontRenderer.drawString((new StringBuilder()).append("Gold Coins: ").append(gold.getGoldTotal(entityplayer)).toString(), 0, 0, x);
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
    		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(player.username, gold.getGoldTotal(player), worthy.getWorthy(player), hunter.getHunterStatus(player))), par1player);
        }
    }

}
