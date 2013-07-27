package aginsun.journey.core.questsystem.quests;

import net.minecraft.item.ItemStack;
import aginsun.journey.core.questsystem.Quest;
import aginsun.journey.core.questsystem.QuestType;

public class QuestAdventureStart extends Quest 
{
	private static String[] StartLines = {""};
	private static String[] EndLines = {"And so the first quest is finished, talk to me again for the second quest!"};
	private static String[] ProgressLines = {""};
	private static String[] QuestName = {"The Start"};
	private static int[] RewardXP = {100};
	private static int[] RewardGold = {100};
	private static ItemStack[] itemstack = {null};
	private static String[][] questDiscription = {new String[]{"The start of your adventure lies here", "You are on your way to becoming a legend"}};
	
	public QuestAdventureStart(int QuestNumber, int index) 
	{
		super(QuestNumber, index, StartLines, EndLines, ProgressLines, QuestName, RewardXP, RewardGold, itemstack, questDiscription);
	}

	@Override
	public QuestType getQuestType() 
	{
		return null;
	}

}
