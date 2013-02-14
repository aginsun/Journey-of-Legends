package aginsun.taleofkingdoms.core.goldSystem;

import java.util.HashMap;
import net.minecraft.entity.player.EntityPlayer;

public class StatKeeper 
{
	public static HashMap<String, Integer> StrengthMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> DexeterityMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> IntelligenceMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> LuckMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> LevelMap = new HashMap<String, Integer>();
	
	public static int getStatPoints(EntityPlayer player, String Stat)
	{
		if(Stat.equals("STR"))
		{
			if(!StrengthMap.containsKey(player.username))
			{
				return 4;
			}
			return StrengthMap.get(player.username);
		}
		if(Stat.equals("DEX"))
		{
			if(!DexeterityMap.containsKey(player.username))
			{
				return 4;
			}
			return DexeterityMap.get(player.username);
		}
		if(Stat.equals("INT"))
		{
			if(!IntelligenceMap.containsKey(player.username))
			{
				return 4;
			}
			return IntelligenceMap.get(player.username);
		}
		if(Stat.equals("LUK"))
		{
			if(!LuckMap.containsKey(player.username))
			{
				return 4;
			}
			return LuckMap.get(player.username);
		}
		if(Stat.equals("LVL"))
		{
			if(!LevelMap.containsKey(player.username))
			{
				return 1;
			}
			return LevelMap.get(player.username);
		}
		return 0;
	}
	
	public static void setStatPoints(EntityPlayer player,String Stat, int amount)
	{
		if(Stat.equals("STR"))
			StrengthMap.put(player.username, amount);
		if(Stat.equals("DEX"))
			DexeterityMap.put(player.username, amount);
		if(Stat.equals("INT"))
			IntelligenceMap.put(player.username, amount);
		if(Stat.equals("LUK"))
			LuckMap.put(player.username, amount);
		if(Stat.equals("LVL"))
			LevelMap.put(player.username, amount);
	}
	
	public static void addStatPoints(EntityPlayer player, String Stat, int amount) 
	{
		int i = getStatPoints(player, Stat);
		i += amount;
		setStatPoints(player, Stat, i);
	}
}
