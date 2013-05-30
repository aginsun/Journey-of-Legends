package aginsun.journey.core.quests;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class QuestFarmer extends Quest 
{
	private static String[] StartLines = {""};
	private static String[] EndLines = {};
	private static String[] ProgressLines = {};
	private static String[] QuestName = {};
	private static int[] RewardXP = {};
	private static int[] RewardGold = {};
	private static ItemStack[] itemstack = {};
	
	public QuestFarmer(int QuestNumber, int index)
	{
		super(QuestNumber, index, StartLines, EndLines, ProgressLines, QuestName, RewardXP, RewardGold, itemstack);
	}

	@Override
	public QuestType getQuestType() 
	{
		return QuestType.GATHERING;
	}
}
