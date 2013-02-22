package aginsun.taleofkingdoms.core.goldSystem;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
	
	public static void setGold(EntityPlayer player, int GoldValue)
	{
		GoldValues.put(player.username, GoldValue);
	}
	
	public static void addGold(EntityPlayer player)
	{
		int i = getGoldTotal(player);
		i++;
		setGold(player, i);
	}
	
	public static void addGold(EntityPlayer player, int amount)
	{
		int i = getGoldTotal(player);
		i += amount;
		setGold(player, i);
	}
	
	public static void removeGold(EntityPlayer player, int amount)
	{
		int i = getGoldTotal(player);
		i -= amount;
		setGold(player, i);
	}
}
