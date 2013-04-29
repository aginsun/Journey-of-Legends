package aginsun.taleofkingdoms.api;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;


/**
 * WIP
 * @author Aginsun
 */
public class LevelKeeper 
{
	private static HashMap<String, Integer> CurrentLevelMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> LevelUpsMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> LevelPointsMap = new HashMap<String, Integer>();
	
	public static void setCurrentLevel(EntityPlayer player, int CurrentLevel)
	{
		CurrentLevelMap.put(player.username, CurrentLevel);
	}
	
	public static int getCurrentLevel(EntityPlayer player) 
	{
		if(!CurrentLevelMap.containsKey(player.username))
		{
			return 1;
		}
		else if(CurrentLevelMap.get(player.username) == 0)
		{
			return 1;
		}
		else
		{
			return CurrentLevelMap.get(player.username);
		}
	}

	public static void setLevelPoints(EntityPlayer player, int levelPoints) 
	{
		LevelPointsMap.put(player.username, levelPoints);
	}
	
	public static int getLevelPoints(EntityPlayer player)
	{
		if(!LevelPointsMap.containsKey(player.username))
		{
			return 0;
		}
		else
		{
			return LevelPointsMap.get(player.username);
		}
	}
	
	public static void decreaseLevelPoints(EntityPlayer player)
	{
		int i = getLevelPoints(player);
		i--;
		setLevelPoints(player, i);
	}
	public static void addLevelPoints(EntityPlayer player, int amount)
	{
		int i = getLevelPoints(player);
		i += amount;
		setLevelPoints(player, i);
	}
}
