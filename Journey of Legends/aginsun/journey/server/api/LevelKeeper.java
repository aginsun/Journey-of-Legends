package aginsun.journey.server.api;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

/**
 * WIP
 * @author Aginsun
 */
public class LevelKeeper 
{
	private static HashMap<String, Integer> LevelMap = new HashMap<String, Integer>();
	private static HashMap<String, Integer> StatPointsMap = new HashMap<String, Integer>();
	
	public static void setLevel(EntityPlayer player, int amount)
	{
		LevelMap.put(player.username, amount);
	}
	
	public static int getLevel(EntityPlayer player)
	{
		if(LevelMap.containsKey(player.username))
			return LevelMap.get(player.username);
		else
			return 1;
	}
	
	public static void addLevel(EntityPlayer player)
	{
		int x = getLevel(player);
		x++;
		setLevel(player, x);
	}
	
	public static void setSP(EntityPlayer player, int amount)
	{
		StatPointsMap.put(player.username, amount);
	}
	
	public static int getSP(EntityPlayer player)
	{
		if(StatPointsMap.containsKey(player.username))
			return StatPointsMap.get(player.username);
		else
			return 0;
	}
	
	public static void addSP(EntityPlayer player, int amount)
	{
		int x = getSP(player);
		x += amount;
		setSP(player, x);
	}
	
	public static void decreaseSP(EntityPlayer player, int amount)
	{
		int x = getSP(player);
		x -= amount;
		setSP(player, x);
	}
}
