package aginsun.journey.core.questsystem.quests;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import aginsun.journey.core.questsystem.Quest;
import aginsun.journey.core.questsystem.QuestType;

public class QuestGuildMaster extends Quest
{
	private static String[] StartLines = {"Open the Stat Screen by pressing O on your keyboard.", "Go and level up!", "Go and kill 10 zombies!", "Go and reach level 10!"};
	private static String[] EndLines = {"Very well, now you are aware where you can upgrade your statpoints!", "Very well, now go and spend your stat points!-" +
								 "Try and decide your choice by what you want to choose, Strength for warrior, Dexerity for hunter, Intelligence for wizard and luck for thief.", 
								 "Thank you for killing them, now my house will not be threathned for a while", null};
	private static String[] ProgressLines = {"Go on, press O on your keyboard", "Go and level, you are not there yet!", "Go on, kill those pesky zombies", "You are on your way to level 10, but you are not yet there."};
	private static String[] QuestName = {"The beginning of a great adventure", "Leveling", "Hunting", "Progression"};
	private static int[] RewardXP = {100, 50, 50, 150};
	private static int[] RewardGold = {10, 50, 50, 100};
	private static ItemStack[] itemstack = {null, null, null, new ItemStack(Item.arrow)};
	private static QuestType[] questType = {QuestType.STUFF, QuestType.STUFF, QuestType.HUNTING, QuestType.STUFF};
	private static String[][] questDiscription = {new String[]{"Learn the basics of the mod!"}, 
												  new String[]{"Leveling is a vital point", "without it you will never", "become a true legend"},
												  new String[]{"My house is under threat", "Can you kill those pesky zombies for me?", "Seems like killing mobs is one way to level up!"},
												  new String[]{"This is the time, where you will have to level up aways", "Reach level 10, and return to me", "Then it shall be time for you to pick your class!"}};
	private int index;
	
	public QuestGuildMaster(int questNumber, int index)
	{
		super(questNumber, index, StartLines, EndLines, ProgressLines, QuestName, RewardXP, RewardGold, itemstack, questDiscription);
		this.index = index;
	}

	@Override
	public QuestType getQuestType() 
	{
		return questType[index];
	}
}
