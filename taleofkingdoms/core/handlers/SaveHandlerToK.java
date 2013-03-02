package aginsun.taleofkingdoms.core.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import aginsun.taleofkingdoms.core.DataStorage;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.HunterKeeper;
import aginsun.taleofkingdoms.core.goldSystem.LevelKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketStats;
import aginsun.taleofkingdoms.core.handlers.packets.PacketToK;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import aginsun.taleofkingdoms.core.handlers.packets.PacketWorthy;
import aginsun.taleofkingdoms.items.InitItems;

public class SaveHandlerToK implements IPlayerTracker
{
	public static int DataSaved = 1;
	private static SaveHandlerToK instance;
	public static int Worthy;
	private GoldKeeper gold;
	private WorthyKeeper worthy;
	private StatKeeper stats;
	private EntityPlayer player;
	private NBTTagCompound data;
	public Player par1player;
	public HunterKeeper hunter;
	private LevelKeeper level;
	
	public SaveHandlerToK()
	{
		instance = this;
	}
	
	@Override
	public void onPlayerLogin(EntityPlayer receiver)
	{
		this.par1player = (Player)receiver;
		getData(receiver);
		if(receiver.username.equals("aginsun"))
		{
			gold.addGold(receiver, 15000000);
			stats.setStrengthPoints(receiver, 1500);
			stats.setDexerityPoints(receiver, 1500);
			stats.setIntelligencePoints(receiver, 1500);
			stats.setLuckPoints(receiver, 1500);
			stats.setLevel(receiver, 200);
			receiver.dropItem(InitItems.ItemAgBladeID, 1);
		}
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(receiver.username, gold.getGoldTotal(receiver))), par1player);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketWorthy(receiver.username, worthy.getWorthy(receiver))), par1player);
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
		data =  player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
		data.setInteger("GoldTotal", gold.getGoldTotal(player));
		data.setFloat("Worthy", worthy.getWorthy(player));
		data.setInteger("Strength", stats.getStrengthPoints(player));
		data.setInteger("DEX", stats.getDexerityPoints(player));
		data.setInteger("INT", stats.getIntelligencePoints(player));
		data.setInteger("LUK", stats.getLuckPoints(player));
		data.setInteger("LVL", stats.getLevel(player));
		data.setInteger("CurrentLVL", level.getCurrentLevel(player));
		data.setInteger("LVLUps", level.getLevelUps(player));
		data.setInteger("LVLPoints", level.getLevelPoints(player));
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
				worthy.setWorthy(player, data.getFloat("Worthy"));
				stats.setStrengthPoints(player, data.getInteger("Strength"));
				stats.setDexerityPoints(player, data.getInteger("DEX"));
				stats.setIntelligencePoints(player, data.getInteger("INT"));
				stats.setLuckPoints(player, data.getInteger("LUK"));
				stats.setLevel(player, data.getInteger("LVL"));
				level.setCurrentLevel(player, data.getInteger("CurrentLVL"));
				level.setLevelUps(player, data.getInteger("LVLUps"));
				level.setLevelPoints(player, data.getInteger("LVLPoints"));
			}
		}
	}
}
