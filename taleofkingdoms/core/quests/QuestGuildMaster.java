package aginsun.taleofkingdoms.core.quests;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import aginsun.taleofkingdoms.api.ExperienceKeeper;
import aginsun.taleofkingdoms.api.GoldKeeper;
import aginsun.taleofkingdoms.core.handlers.PickupHandler;

public class QuestGuildMaster implements Quest
{
	@Override
	public String QuestStartLines(int number) 
	{
		if(number == 1)
			return "Guild Master: Open up your stat screen by pressing O on your keyboard!";
		else
			return "You should not see this, incase you do, report on the forum thread, with quest name + ";
	}

	@Override
	public void QuestEndReward(EntityPlayer player, int number)
	{
		if(number == 1 && FMLCommonHandler.instance().getEffectiveSide().isServer())
		{
			GoldKeeper.addGold(player, 50);
			ExperienceKeeper.addExperience(player, 100);
			PickupHandler.checkLevelUps(player);
		}
	}

	@Override
	public String QuestStartLines(EntityPlayer player, int number)
	{
		return null;
	}

	@Override
	public String QuestEndLines(int number)
	{
		return null;
	}

	@Override
	public String QuestEndLines(EntityPlayer player, int number) 
	{
		if(number == 1)
			return "Very well " + player.username + ", now you know where to upgrade your stats when you level up!";
		else
			return "You should not see this, incase you do, report on the forum thread, with quest name";
	}
}
