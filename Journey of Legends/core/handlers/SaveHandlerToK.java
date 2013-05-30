package aginsun.journey.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import aginsun.journey.api.ExperienceKeeper;
import aginsun.journey.api.GoldKeeper;
import aginsun.journey.api.LevelKeeper;
import aginsun.journey.api.QuestHandler;
import aginsun.journey.api.StatKeeper;
import aginsun.journey.core.handlers.packets.PacketExperience;
import aginsun.journey.core.handlers.packets.PacketGold;
import aginsun.journey.core.handlers.packets.PacketStats;
import aginsun.journey.core.handlers.packets.PacketType;
import aginsun.journey.items.InitItems;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class SaveHandlerToK implements IPlayerTracker
{
	public static int DataSaved = 1;
	private static SaveHandlerToK instance;
	public static int Worthy;
	private GoldKeeper gold;
	private ExperienceKeeper experience;
	private StatKeeper stats;
	private EntityPlayer player;
	private NBTTagCompound data;
	public Player par1player;
	private LevelKeeper level;
	private RaceKeeper race;
	
	public SaveHandlerToK()
	{
		instance = this;
	}
	
	@Override
	public void onPlayerLogin(EntityPlayer receiver)
	{
		this.par1player = (Player)receiver;
		getData(receiver);
		/*if(receiver.username.equals("aginsun"))
		{
			gold.addGold(receiver, 15000000);
			stats.setStrengthPoints(receiver, 1500);
			stats.setDexerityPoints(receiver, 1500);
			stats.setIntelligencePoints(receiver, 1500);
			stats.setLuckPoints(receiver, 1500);
			stats.setLevel(receiver, 200);
			receiver.dropItem(InitItems.ItemAgBladeID, 1);
		}*/
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(receiver.username, gold.getGoldTotal(receiver))), par1player);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketExperience(receiver.username, experience.getExperience(receiver))), par1player);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketStats(receiver.username, stats.getStrengthPoints(receiver), 
																										 stats.getDexerityPoints(receiver), 
																										 stats.getIntelligencePoints(receiver), 
																										 stats.getLuckPoints(receiver), 
																										 stats.getLevel(receiver))), par1player);
	}
	
	@Override
	public void onPlayerLogout(EntityPlayer receiver)
	{
		SetData(receiver);
	}
	@Override
	public void onPlayerChangedDimension(EntityPlayer player){}
	@Override
	public void onPlayerRespawn(EntityPlayer player){}
	
	public void SetData(EntityPlayer player)
	{
		data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
		data.setInteger("GoldTotal", gold.getGoldTotal(player));
		data.setInteger("Worthy", experience.getExperience(player));
		data.setInteger("Strength", stats.getStrengthPoints(player));
		data.setInteger("DEX", stats.getDexerityPoints(player));
		data.setInteger("INT", stats.getIntelligencePoints(player));
		data.setInteger("LUK", stats.getLuckPoints(player));
		data.setInteger("LVL", stats.getLevel(player));
		data.setInteger("CurrentLVL", level.getCurrentLevel(player));
		data.setInteger("LVLPoints", level.getLevelPoints(player));
		data.setString("Race", race.getClass(player));
		data.setCompoundTag("Quests", QuestHandler.instance().getQuestPlayer(player));
		player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, data);
	}
	
	public void getData(EntityPlayer player)
	{
		if(player != null)
		{
			data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
			if(data != null)
			{
				gold.setGold(player, data.getInteger("GoldTotal"));
				experience.setExperience(player, data.getInteger("Worthy"));
				stats.setStrengthPoints(player, data.getInteger("Strength"));
				stats.setDexerityPoints(player, data.getInteger("DEX"));
				stats.setIntelligencePoints(player, data.getInteger("INT"));
				stats.setLuckPoints(player, data.getInteger("LUK"));
				stats.setLevel(player, data.getInteger("LVL"));
				level.setCurrentLevel(player, data.getInteger("CurrentLVL"));
				level.setLevelPoints(player, data.getInteger("LVLPoints"));
				race.setClass(player, data.getString("Race"));
				QuestHandler.instance().setQuestPlayer(player, data.getCompoundTag("Quests"));
			}
		}
	}
}