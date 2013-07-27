package aginsun.journey.core.questsystem.quests;

import net.minecraft.item.ItemStack;
import aginsun.journey.core.questsystem.Quest;
import aginsun.journey.core.questsystem.QuestType;

public class QuestFarmer extends Quest 
{
	private static String[] StartLines = {""};
	private static String[] EndLines = {};
	private static String[] ProgressLines = {};
	private static String[] QuestName = {};
	private static int[] RewardXP = {};
	private static int[] RewardGold = {};
	private static ItemStack[] itemstack = {};
	private static String[][] questDiscription = {new String[]{""}};

	public QuestFarmer(int QuestNumber, int index)
	{
		super(QuestNumber, index, StartLines, EndLines, ProgressLines, QuestName, RewardXP, RewardGold, itemstack, questDiscription);
	}

	@Override
	public QuestType getQuestType() 
	{
		return QuestType.GATHERING;
	}
}
