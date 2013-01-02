package aginsun.taleofkingdoms.core;

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
	
	/*public static void DecreaseGold() //Might not be a use for this, but it is there in case people want to use it!
	{
		if(GoldTotal >= 1)
		{
			GoldTotal--;
		}
	}
	
	public static void DecreaseGold(int i)
	{
		if(GoldTotal >= i && i > 0)
		{
			GoldTotal -= i;
		}
		else if(GoldTotal < i && i > 0)
		{
			//Do nothing?
		}
		else
		{
			Exception e = new Exception();			
			e.printStackTrace();
		}		
	}*/
	
	public static int PriceItem(String s)
	{
		if(s.equals("tile.dirt"))
		{
			return 1;
		}
		
		return 0;
	}
}
