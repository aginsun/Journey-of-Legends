package aginsun.journey.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import cpw.mods.fml.common.FMLCommonHandler;

import aginsun.journey.core.handlers.SaveHandlerToK;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
/**
 * WIP: QuestHandler: 1 == QuestStarted, 2 == QuestFinished, 3 == QuestFinished + reward!
 * @author Aginsun
 */

public class QuestHandler
{
	private HashMap<String, NBTTagCompound> questList = new HashMap<String, NBTTagCompound>();
	private static QuestHandler instance = new QuestHandler();
	
	public QuestHandler()
	{
	}
	
	public static QuestHandler instance()
	{
		return instance;
	}
	
	public int getQuestStatus(EntityPlayer player, String QuestName)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		if(nbt != null)
		{
			int x = nbt.getInteger(QuestName);
			return x;
		}
		return 0;
	}
	
	public void setQuestStarted(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 1);
		setQuestPlayer(player, nbt);
	}
	
	public void setQuestFinished(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 2);
		setQuestPlayer(player, nbt);
	}
	
	public void questFinishedReward(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 3);
		setQuestPlayer(player, nbt);
	}
	
	public void setQuestPlayer(EntityPlayer player, NBTTagCompound nbt)
	{
		System.out.println("Added player: " + player.username + " to the QuestMap");
		if(nbt == null)
			System.out.println("nbt zero?");
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
}
