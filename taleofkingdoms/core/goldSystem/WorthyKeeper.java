package aginsun.taleofkingdoms.core.goldSystem;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class WorthyKeeper
{
	private static HashMap<String, Integer> WorthyMap = new HashMap<String, Integer>();
	
	public static void addWorthy(EntityPlayer player)
	{
		int Worthy = getWorthy(player);
		Worthy++;
		setWorthy(player, Worthy);
	}
	
	public static void addWorthy(EntityPlayer player, int Worthy)
	{
		int worthy = getWorthy(player);
		worthy += Worthy;
		setWorthy(player, worthy);
	}
	
	public static void setWorthy(EntityPlayer player, int worthy)
	{
		WorthyMap.put(player.username, worthy);
	}
	
	public static int getWorthy(EntityPlayer player)
	{
		if(!WorthyMap.containsKey(player.username))
		{
			return 0;
		}
		return WorthyMap.get(player.username);
	}
}
