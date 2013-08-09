package aginsun.journey.server.api;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import aginsun.journey.universal.packets.PacketQuestDataClient;
import aginsun.journey.universal.packets.PacketType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**
 * WIP: QuestHandler: 1 == QuestStarted, 2 == QuestFinished, 3 == QuestFinished + reward!
 * @author Aginsun
 */

public class QuestHandler
{
	private HashMap<String, NBTTagCompound> questList = new HashMap<String, NBTTagCompound>();
	private HashMap<String, HashMap<String, Integer>> questProgress = new HashMap<String, HashMap<String, Integer>>();
	private static QuestHandler instance = new QuestHandler();
	
	public QuestHandler(){}
	
	public static QuestHandler instance()
	{
		return instance;
	}
	
	public int getQuestStatus(EntityPlayer player, String QuestName)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		return nbt.getInteger(QuestName);
	}
	
	public void setQuestStatus(EntityPlayer player, String questName, int questStatus)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(questName, questStatus);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketQuestDataClient(player.username, questName, questStatus)), (Player)player);
		setQuestPlayer(player, nbt);
	}
	
	public void setQuestStarted(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 1);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketQuestDataClient(player.username, quest, 1)), (Player)player);
		setQuestPlayer(player, nbt);
	}
	
	public void setQuestFinished(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 2);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketQuestDataClient(player.username, quest, 2)), (Player)player);
		setQuestPlayer(player, nbt);
	}
	
	public void questFinishedReward(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 3);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketQuestDataClient(player.username, quest, 3)), (Player)player);
		setQuestPlayer(player, nbt);
	}
	
	public void setQuestPlayer(EntityPlayer player, NBTTagCompound nbt)
	{
		System.out.println("Added player: " + player.username + " to the QuestMap");
		getMap().put(player.username, nbt);
	}
	
	public boolean isQuestActive(EntityPlayer player, String questName)
	{
		if(getQuestStatus(player, questName) != 3 && getQuestStatus(player, questName) != 0)
			return true;
		return false;
	}
	
	public NBTTagCompound getQuestPlayer(EntityPlayer player)
	{	
		if(getMap().containsKey(player.username))
		{
			System.out.println("Does have the player in there");
			return getMap().get(player.username);
		}
		return null;
	}
	
	public HashMap<String, NBTTagCompound> getMap()
	{
		return questList;
	}
	
	@SideOnly(Side.CLIENT)
	public void setQuestStatusClient(EntityPlayer player, String questName, int questStatus) 
	{
		HashMap<String, Integer> map = questProgress.get(player.username);
		if(map == null)
			map = new HashMap<String, Integer>();
		map.put(questName, questStatus);
		questProgress.put(player.username, map);
	}
	
	@SideOnly(Side.CLIENT)
	public int getQuestStatusClient(EntityPlayer player, String questName)
	{
		HashMap<String, Integer> map;
		if(questProgress.containsKey(player.username))
		{
			map = questProgress.get(player.username);
		}
		else
			map = new HashMap<String, Integer>();
		
		if(map.containsKey(questName))
			return map.get(questName);
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isQuestActiveClient(EntityPlayer player, String questName)
	{
		if(getQuestStatusClient(player, questName) != 3 && getQuestStatusClient(player, questName) != 0)
			return true;
		return false;
	}
}
