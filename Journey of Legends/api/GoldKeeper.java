package aginsun.journey.api;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
/**
 * addGold and remove gold are the only 2 you should use!
 * 
 * @author Aginsun
 */
public class GoldKeeper
{
	private static HashMap<String, Integer> GoldValues = new HashMap<String, Integer>();
	
	public static int getGoldTotal(EntityPlayer player)
	{
		if(!GoldValues.containsKey(player.username))
		{
			return 0;
		}
		return GoldValues.get(player.username);
	}
	
	public static int getGoldTotal(String username)
	{
		if(!GoldValues.containsKey(username))
		{
			return 0;
		}
		return GoldValues.get(username);
	}
	
	public static void setGold(EntityPlayer player, int GoldValue)
	{
		GoldValues.put(player.username, GoldValue);
	}
	
	
	/**
	 * Adds 1 gold coin
	 * @param player
	 */
	public static void addGold(EntityPlayer player)
	{
		int i = getGoldTotal(player);
		i++;
		setGold(player, i);
	}
	
	public static void addGold(String username, int amount)
	{
		int i = GoldValues.get(username);
		i += amount;
		GoldValues.put(username, i);
	}
	
	/**
	 * Add a specific amount of gold
	 * 
	 * @param player
	 * @param amount
	 */
	public static void addGold(EntityPlayer player, int amount)
	{
		int i = getGoldTotal(player);
		i += amount;
		setGold(player, i);
	}
	
	/**
	 * Remove a specific amount of gold coins
	 * @param player
	 * @param amount
	 */
	public static void removeGold(EntityPlayer player, int amount)
	{
		int i = getGoldTotal(player);
		i -= amount;
		setGold(player, i);
	}
}
