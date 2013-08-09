package aginsun.journey.server.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import aginsun.journey.server.api.ExperienceKeeper;
import aginsun.journey.server.api.GoldKeeper;
import aginsun.journey.server.api.LevelKeeper;
import aginsun.journey.server.api.QuestHandler;
import aginsun.journey.server.api.RaceKeeper;
import aginsun.journey.server.api.StatKeeper;
import aginsun.journey.universal.handlers.QuestRegistry;
import aginsun.journey.universal.packets.PacketExperience;
import aginsun.journey.universal.packets.PacketGold;
import aginsun.journey.universal.packets.PacketQuestDataClient;
import aginsun.journey.universal.packets.PacketStatsClient;
import aginsun.journey.universal.packets.PacketType;
import aginsun.journey.universal.utils.Quest;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class SaveHandler implements IPlayerTracker
{
	public static int DataSaved = 1;
	private static SaveHandler instance;
	public static int Worthy;
	private GoldKeeper gold;
	private ExperienceKeeper experience;
	private StatKeeper stats;
	private EntityPlayer player;
	private NBTTagCompound data;
	public Player par1player;
	private LevelKeeper level;
	private RaceKeeper race;
	
	public SaveHandler()
	{
		instance = this;
	}
	
	@Override
	public void onPlayerLogin(EntityPlayer receiver)
	{
		this.par1player = (Player)receiver;
		getData(receiver);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(receiver.username, gold.getGoldTotal(receiver))), par1player);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketExperience(receiver.username, experience.getExperience(receiver))), par1player);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketStatsClient(receiver.username, stats.getStrengthPoints(receiver), 	
																											stats.getDexerityPoints(receiver), 
																											stats.getIntelligencePoints(receiver), 
																											stats.getLuckPoints(receiver))), par1player);
				
				
		for(int i = 1; i <= QuestRegistry.getMap().size(); i++)
		{
			Quest quest = QuestRegistry.getQuest(i).setPlayer(player);
			PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketQuestDataClient(receiver.username, quest.getQuestName(), QuestHandler.instance().getQuestStatus(receiver, quest.getQuestName()))), par1player);
		}
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
		data.setInteger("LVL", LevelKeeper.getLevel(player));
		data.setInteger("SP", LevelKeeper.getSP(player));
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
				race.setClass(player, data.getString("Race"));
				LevelKeeper.setLevel(player, data.getInteger("LVL"));
				LevelKeeper.setSP(player, data.getInteger("SP"));
				QuestHandler.instance().setQuestPlayer(player, data.getCompoundTag("Quests"));
			}
		}
	}
}
