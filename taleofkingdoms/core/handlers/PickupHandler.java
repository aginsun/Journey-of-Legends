package aginsun.taleofkingdoms.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import aginsun.taleofkingdoms.api.ExperienceKeeper;
import aginsun.taleofkingdoms.api.GoldKeeper;
import aginsun.taleofkingdoms.api.LevelKeeper;
import aginsun.taleofkingdoms.api.QuestHandler;
import aginsun.taleofkingdoms.api.StatKeeper;
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
	            ExperienceKeeper.addExperience(event.entityPlayer);
	    		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(event.entityPlayer.username, GoldKeeper.getGoldTotal(event.entityPlayer))), par1player);
	    		checkLevelUps(event.entityPlayer);
	        }
		}
	}
	
	public static void checkLevelUps(EntityPlayer player)
	{
		int experience = ExperienceKeeper.getExperience(player);
		int lvl = Math.round(experience / 850);
		int CurrentLvl = StatKeeper.getLevel(player);
		if(++lvl != CurrentLvl)
		{
			StatKeeper.setLevel(player, lvl);
			if(QuestHandler.getQuestStatus(player, "Leveling") != 0 && QuestHandler.getQuestStatus(player, "Leveling") != 3);
				QuestHandler.setQuestFinished(player, "Leveling");
			LevelKeeper.addLevelPoints(player, 5);
			if(FMLCommonHandler.instance().getEffectiveSide().isServer())
				System.out.println("Player: " + player.username + " Level: " + lvl);
		}
	}
}
