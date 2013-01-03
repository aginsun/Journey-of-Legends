package aginsun.taleofkingdoms.core.goldSystem;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class HunterKeeper 
{
	public static HashMap<String, Boolean> HunterMap = new HashMap<String, Boolean>();
	
	public static boolean getHunterStatus(EntityPlayer player)
	{
		if(!HunterMap.containsKey(player.username))
		{
			return false;
		}
		return HunterMap.get(player.username);
	}
	
	public static void setHunterStatus(EntityPlayer player, boolean Hunterstatus)
	{
		HunterMap.put(player.username, Hunterstatus);
	}
}
