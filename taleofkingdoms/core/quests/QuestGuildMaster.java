package aginsun.taleofkingdoms.core.quests;

import java.lang.reflect.Array;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import aginsun.taleofkingdoms.api.ExperienceKeeper;
import aginsun.taleofkingdoms.api.GoldKeeper;
import aginsun.taleofkingdoms.core.handlers.PickupHandler;

public class QuestGuildMaster extends Quest
{
	private String[] StartLines = {"Open the Stat Screen by pressing O on your keyboard.", "Go and level up!", "Go and kill 10 zombies!"};
	private String[] EndLines = {"Very well, now you are aware where you can upgrade your statpoints!", "Very well, now go and spend your stat points!-" +
								 "Try and decide your choice by what you want to choose, Strength for warrior, Dexerity for hunter, Intelligence for wizard and luck for thief.", 
								 "Thank you for killing them, now the guild will not be threathned for a while"};
	private String[] ProgressLines = {"Go on, press O on your keyboard", "Go and level, you are not there yet!", "Go on, kill those pesky zombies"};
	private String[] QuestName = {"The beginning of a great adventure", "Leveling", "Hunting"};
	private int[] RewardXP = {100, 50, 50};
	private int[] RewardGold = {10, 50, 50};
	private ItemStack[] itemstack = {null, null, null};
	private int index;
	
	public QuestGuildMaster(int questNumber, int index)
	{
		super(questNumber);
		this.index = index;
	}
	
	@Override
	public String questStartLines(EntityPlayer player) 
	{
		return StartLines[index];
	}

	@Override
	public String questEndLines(EntityPlayer player) 
	{
		return EndLines[index];
	}

	@Override
	public int questEndRewardXP(EntityPlayer player)
	{
		return RewardXP[index];
	}
	
	@Override
	public int questEndRewardGold(EntityPlayer player)
	{
		return RewardGold[index];
	}
	
	@Override
	public ItemStack questEndRewardItemStacks(EntityPlayer player)
	{
		return itemstack[index];
	}

	@Override
	public String questProgressLine(EntityPlayer player) 
	{
		return ProgressLines[index];
	}

	@Override
	public String getQuestName() 
	{
		return QuestName[index];
	}
}
