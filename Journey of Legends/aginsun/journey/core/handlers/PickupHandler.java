package aginsun.journey.core.handlers;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import aginsun.journey.api.ExperienceKeeper;
import aginsun.journey.api.GoldKeeper;
import aginsun.journey.api.LevelKeeper;
import aginsun.journey.api.QuestHandler;
import aginsun.journey.core.handlers.packets.PacketExperience;
import aginsun.journey.core.handlers.packets.PacketGold;
import aginsun.journey.core.handlers.packets.PacketLevel;
import aginsun.journey.core.handlers.packets.PacketType;
import aginsun.journey.core.questsystem.QuestRegistry;
import aginsun.journey.items.InitItems;
import aginsun.journey.party.Party;
import aginsun.journey.party.PartyHandler;
import cpw.mods.fml.common.IPickupNotifier;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PickupHandler implements IPickupNotifier
{
	private Player par1player;
	
	public void checkLevelUps(EntityPlayer player)
	{
		int level = LevelKeeper.getLevel(player);
		int exp = ExperienceKeeper.getExperience(player);
		int levelShouldbe = (Math.round(exp / 850) + 1);
		System.out.println("Level: " + level + " EXP: " + exp + " LevelShoudbe: " + levelShouldbe);
		if(level < levelShouldbe)
		{
			if(QuestHandler.instance().isQuestActive(player, QuestRegistry.getQuest(2).getQuestName()))
				QuestHandler.instance().setQuestFinished(player, QuestRegistry.getQuest(2).getQuestName());
			if(levelShouldbe == 10)
			{
				if(QuestHandler.instance().isQuestActive(player, QuestRegistry.getQuest(5).getQuestName()))
					QuestHandler.instance().setQuestFinished(player, QuestRegistry.getQuest(5).getQuestName());
			}
			LevelKeeper.addLevel(player);
			if(level != 0)
				LevelKeeper.addSP(player, 4);
			PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketLevel(player.username, LevelKeeper.getLevel(player), LevelKeeper.getSP(player))), (Player)player);
		}
	}

	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player) 
	{
		ItemStack itemstack = item.getEntityItem();
		if(itemstack.itemID == InitItems.ItemCoins.itemID)
		{
			player.inventory.consumeInventoryItem(itemstack.itemID);
			if(PartyHandler.getInstance().isPlayerInParty(player.username))
			{
				Party party = PartyHandler.getInstance().getParty(player.username);
				List<String> list = party.playerList;
				for(String string : list)
				{
					GoldKeeper.addGold(string, Math.round(15 / list.size()));
		            PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(string, GoldKeeper.getGoldTotal(string))), (Player)player);
		            PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketExperience(string, ExperienceKeeper.getExperience(string))), (Player)player);
		    		checkLevelUps(player);
		    		return;
				}
			}
			else
			{
				GoldKeeper.addGold(player);
				this.par1player = (Player)player;
	            ExperienceKeeper.addExperience(player);
	            PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(player.username, GoldKeeper.getGoldTotal(player))), (Player)player);
	            PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketExperience(player.username, ExperienceKeeper.getExperience(player))), (Player)player);
	    		checkLevelUps(player);
			}
    	}
	}
}
