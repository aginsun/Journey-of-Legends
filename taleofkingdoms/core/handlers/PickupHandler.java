package aginsun.taleofkingdoms.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import aginsun.taleofkingdoms.client.guis.GuiRaceSelect;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.LevelKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import aginsun.taleofkingdoms.items.InitItems;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PickupHandler
{
	private Player par1player;
	@ForgeSubscribe
	public void onEntityItemPickup(EntityItemPickupEvent event) 
	{
		if(event.entityPlayer != null)
		{
			ItemStack itemstack = event.item.getEntityItem();
	        if (itemstack.itemID == InitItems.ItemCoins.itemID)
	        {
	            InventoryPlayer inventoryplayer = event.entityPlayer.inventory;
	            inventoryplayer.consumeInventoryItem(itemstack.itemID);
	            GoldKeeper.addGold(event.entityPlayer);
	    		this.par1player = (Player)event.entityPlayer;
	            WorthyKeeper.addWorthy(event.entityPlayer);
	    		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(event.entityPlayer.username, GoldKeeper.getGoldTotal(event.entityPlayer))), par1player);
	    		checkLevelUps(event.entityPlayer);
	        }
		}
	}
	
	public void checkLevelUps(EntityPlayer player)
	{
		int worthy = WorthyKeeper.getWorthy(player);
		int lvl = Math.round(worthy / 850);
		int CurrentLvl = StatKeeper.getLevel(player);
		if(++lvl != CurrentLvl)
		{
			StatKeeper.setLevel(player, lvl);
			LevelKeeper.addLevelPoints(player, 5);
			if(FMLCommonHandler.instance().getEffectiveSide().isServer())
				System.out.println("Player: " + player.username + " Level: " + lvl);
		}
	}
}
