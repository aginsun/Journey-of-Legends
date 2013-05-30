package aginsun.journey.core.handlers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import aginsun.journey.api.ExperienceKeeper;
import aginsun.journey.api.GoldKeeper;
import aginsun.journey.api.LevelKeeper;
import aginsun.journey.api.QuestHandler;
import aginsun.journey.api.StatKeeper;
import aginsun.journey.core.handlers.packets.PacketGold;
import aginsun.journey.core.handlers.packets.PacketType;
import aginsun.journey.items.InitItems;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPickupNotifier;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PickupHandler implements IPickupNotifier
{
	private Player par1player;
	
	public static void checkLevelUps(EntityPlayer player)
	{
		int experience = ExperienceKeeper.getExperience(player);
		int lvl = Math.round(experience / 850);
		int CurrentLvl = StatKeeper.getLevel(player);
		if(++lvl != CurrentLvl)
		{
			StatKeeper.setLevel(player, lvl);
			if(QuestHandler.instance().isQuestActive(player, "Leveling"));
				QuestHandler.instance().setQuestFinished(player, "Leveling");
			LevelKeeper.addLevelPoints(player, 5);
			if(FMLCommonHandler.instance().getEffectiveSide().isServer())
				System.out.println("Player: " + player.username + " Level: " + lvl);
		}
	}

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player) 
	{
		ItemStack itemstack = item.getEntityItem();
		if(itemstack.itemID == InitItems.ItemCoins.itemID)
		{
			player.inventory.consumeInventoryItem(itemstack.itemID);
			GoldKeeper.addGold(player);
			this.par1player = (Player)player;
            ExperienceKeeper.addExperience(player);
    		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(player.username, GoldKeeper.getGoldTotal(player))), par1player);
    		checkLevelUps(player);
    	}
	}
}
