package aginsun.journey.api;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class ExperienceKeeper
{
	private static HashMap<String, Integer> ExperienceMap = new HashMap<String, Integer>();
	
	public static void addExperience(EntityPlayer player)
	{
		int Experience = getExperience(player);
		Experience++;
		setExperience(player, Experience);
	}
	
	public static void addExperience(EntityPlayer player, int experience)
	{
		int Experience = getExperience(player);
		Experience += experience;
		setExperience(player, Experience);
	}
	
	public static void setExperience(EntityPlayer player, int experience)
	{
		ExperienceMap.put(player.username, experience);
	}
	
	public static int getExperience(EntityPlayer player)
	{
		if(!ExperienceMap.containsKey(player.username))
		{
			return 0;
		}
		return ExperienceMap.get(player.username);
	}
}
