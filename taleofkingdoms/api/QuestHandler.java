package aginsun.taleofkingdoms.api;

import java.util.HashMap;

import aginsun.taleofkingdoms.core.handlers.SaveHandlerToK;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
/**
 * WIP: QuestHandler: 1 == QuestStarted, 2 == QuestFinished, 3 == QuestFinished + reward!
 * @author Aginsun
 */

public class QuestHandler
{
	public static HashMap<String, NBTTagCompound> questList = new HashMap<String, NBTTagCompound>();
	
	public static int getQuestStatus(EntityPlayer player, String QuestName)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		if(nbt.hasKey(QuestName))
			return nbt.getInteger(QuestName);
		return 0;
	}
	
	public static void setQuestStarted(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 1);
		setQuestPlayer(player, nbt);
	}
	
	public static void setQuestFinished(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 2);
		setQuestPlayer(player, nbt);
	}
	
	public static void questFinishedReward(EntityPlayer player, String quest)
	{
		NBTTagCompound nbt = getQuestPlayer(player);
		nbt.setInteger(quest, 3);
		setQuestPlayer(player, nbt);
	}
	
	public static void setQuestPlayer(EntityPlayer player, NBTTagCompound nbt)
	{
		questList.put(player.username, nbt);
	}
	
	public static NBTTagCompound getQuestPlayer(EntityPlayer player)
	{
		if(questList.containsKey(player.username))
			return questList.get(player.username);
		return null;
	}
}
