package aginsun.journey.core.quests;

import java.lang.reflect.Array;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import aginsun.journey.api.ExperienceKeeper;
import aginsun.journey.api.GoldKeeper;
import aginsun.journey.core.handlers.PickupHandler;

public class QuestGuildMaster extends Quest
{
	private static String[] StartLines = {"Open the Stat Screen by pressing O on your keyboard.", "Go and level up!", "Go and kill 10 zombies!"};
	private static String[] EndLines = {"Very well, now you are aware where you can upgrade your statpoints!", "Very well, now go and spend your stat points!-" +
								 "Try and decide your choice by what you want to choose, Strength for warrior, Dexerity for hunter, Intelligence for wizard and luck for thief.", 
								 "Thank you for killing them, now the guild will not be threathned for a while"};
	private static String[] ProgressLines = {"Go on, press O on your keyboard", "Go and level, you are not there yet!", "Go on, kill those pesky zombies"};
	private static String[] QuestName = {"The beginning of a great adventure", "Leveling", "Hunting"};
	private static int[] RewardXP = {100, 50, 50};
	private static int[] RewardGold = {10, 50, 50};
	private static ItemStack[] itemstack = {null, null, null};
	private static QuestType[] questType = {QuestType.STUFF, QuestType.STUFF, QuestType.HUNTING};
	private int index;
	
	public QuestGuildMaster(int questNumber, int index)
	{
		super(questNumber, index, StartLines, EndLines, ProgressLines, QuestName, RewardXP, RewardGold, itemstack);
		this.index = index;
	}

	@Override
	public QuestType getQuestType() 
	{
		return questType[index];
	}
}
