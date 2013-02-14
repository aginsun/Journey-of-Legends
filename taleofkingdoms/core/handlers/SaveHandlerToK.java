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
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketStats;
import aginsun.taleofkingdoms.core.handlers.packets.PacketToK;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import aginsun.taleofkingdoms.core.handlers.packets.PacketWorthy;

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
			gold.addGold(receiver, 150000);
			worthy.addWorthy(receiver, 15000F);
			stats.addStatPoints(receiver, "STR", 150);
			stats.addStatPoints(receiver, "DEX", 150);
			stats.addStatPoints(receiver, "INT", 150);
			stats.addStatPoints(receiver, "LUK", 150);
			stats.addStatPoints(receiver, "LVL", 150);
		}
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(receiver.username, gold.getGoldTotal(receiver))), par1player);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketWorthy(receiver.username, worthy.getWorthy(receiver))), par1player);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketStats(receiver.username, stats.getStatPoints(receiver, "STR"), stats.getStatPoints(receiver, "DEX"), stats.getStatPoints(receiver, "INT"), stats.getStatPoints(receiver, "LUK"), stats.getStatPoints(receiver, "LVL"))), par1player);
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
		data.setInteger("STR", stats.getStatPoints(player, "STR"));
		data.setInteger("DEX", stats.getStatPoints(player, "DEX"));
		data.setInteger("INT", stats.getStatPoints(player, "INT"));
		data.setInteger("LUK", stats.getStatPoints(player, "LUK"));
		data.setInteger("LVL", stats.getStatPoints(player, "LVL"));
		player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, data);
	}
	
	public void getData(EntityPlayer player)
	{
		if(player != null)
		{
			data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
			if(data != null)
			{
				int i = data.getInteger("GoldTotal");
				gold.setGold(player, i);
				float j = data.getFloat("Worthy");
				worthy.setWorthy(player, j);
				int k = data.getInteger("STR");
				stats.setStatPoints(player, "STR", k);
				int l = data.getInteger("DEX");
				stats.setStatPoints(player, "DEX", l);
				int m = data.getInteger("INT");
				stats.setStatPoints(player, "INT", m);
				int n = data.getInteger("LUK");
				stats.setStatPoints(player, "LUK", n);
				int o = data.getInteger("LVL");
				stats.setStatPoints(player, "LVL", o);
			}
		}
	}
}
