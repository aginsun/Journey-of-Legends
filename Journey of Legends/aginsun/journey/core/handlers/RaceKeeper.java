package aginsun.journey.core.handlers;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class RaceKeeper 
{
	public static HashMap<String, String> Race = new HashMap<String, String>();
	
	public static String getClass(EntityPlayer player)
	{
		if(Race.containsKey(player.username))
			return Race.get(player.username);
		else
			return "Beginner";
	}
	
	public static void setClass(EntityPlayer player, String race)
	{
		Race.put(player.username, race);
	}
}
