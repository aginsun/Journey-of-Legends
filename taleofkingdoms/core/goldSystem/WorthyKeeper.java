package aginsun.taleofkingdoms.core.goldSystem;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class WorthyKeeper
{
	private static HashMap<String, Float> WorthyMap = new HashMap<String, Float>();
	
	public static void addWorthy(EntityPlayer player)
	{
		float Worthy = getWorthy(player);
		Worthy++;
		setWorthy(player, Worthy);
	}
	
	public static void addWorthy(EntityPlayer player, float Worthy)
	{
		float worthy = getWorthy(player);
		worthy += Worthy;
		setWorthy(player, worthy);
	}
	
	public static void setWorthy(EntityPlayer player, float worthy)
	{
		WorthyMap.put(player.username, worthy);
	}
	
	public static float getWorthy(EntityPlayer player)
	{
		if(!WorthyMap.containsKey(player.username))
		{
			return 0;
		}
		return WorthyMap.get(player.username);
	}
}
