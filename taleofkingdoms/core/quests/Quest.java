package aginsun.taleofkingdoms.core.quests;

import cpw.mods.fml.common.FMLCommonHandler;
import aginsun.taleofkingdoms.api.ExperienceKeeper;
import aginsun.taleofkingdoms.api.GoldKeeper;
import aginsun.taleofkingdoms.api.QuestHandler;
import aginsun.taleofkingdoms.client.guis.GuiQuest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public abstract class Quest
{
	private EntityPlayer player;
	
	private int QuestNumber;
	private int index;
	private String[] StartLines;
	private String[] EndLines;
	private String[] ProgressLines;
	private String[] QuestName;
	private int[] RewardXP;
	private int[] RewardGold;
	private ItemStack[] itemstack;
	
	public Quest(int QuestNumber)
	{
		this.QuestNumber = QuestNumber;
	}
	
	public Quest(int QuestNumber, int index, String[] StartLines, String[] EndLines, String[] ProgressLines, String[] QuestName, int[] RewardXP, int[] RewardGold, ItemStack[] itemstack)
	{
		this(QuestNumber);
		this.index = index;
		this.StartLines = StartLines;
		this.EndLines = EndLines;
		this.ProgressLines = ProgressLines;
		this.QuestName = QuestName;
		this.RewardXP = RewardXP;
		this.RewardGold = RewardGold;
		this.itemstack = itemstack;
	}
	
	public void update()
	{
		int QuestStatus = QuestHandler.getQuestStatus(player, getQuestName());
		
		if(QuestStatus == 0){
			FMLCommonHandler.instance().showGuiScreen(new GuiQuest(getQuestName(), this, false));}
		else if(QuestStatus == 1){
			if(!player.worldObj.isRemote)
				player.addChatMessage(this.questProgressLine(player));}
		else if(QuestStatus == 2){
			FMLCommonHandler.instance().showGuiScreen(new GuiQuest(getQuestName(), this, true));}
		else{
			if(!player.worldObj.isRemote)
				player.addChatMessage(this.standardLine());}
	}
	
	
	public Quest setPlayer(EntityPlayer player)
	{
		this.player = player;
		return this;
	}
	
	private String standardLine()
	{
		return "I currently do not have any quests for you.";
	}
	
	public void questEndReward(EntityPlayer player)
	{
		GoldKeeper.addGold(player, questEndRewardGold(player));
		ExperienceKeeper.addExperience(player, questEndRewardXP(player));
		if(questEndRewardItemStacks(player) != null)
		{
			player.dropItem(questEndRewardItemStacks(player).itemID, questEndRewardItemStacks(player).stackSize);
		}
	}
		
	public String questStartLines(EntityPlayer player)
	{
		return StartLines[index];
	}
	
	public String questEndLines(EntityPlayer player)
	{
		return EndLines[index];
	}
	
	public String questProgressLine(EntityPlayer player)
	{
		return ProgressLines[index];
	}
	
	public int questEndRewardXP(EntityPlayer player)
	{
		return RewardXP[index];
	}
	
	public int questEndRewardGold(EntityPlayer player)
	{
		return RewardGold[index];
	}
	
	public ItemStack questEndRewardItemStacks(EntityPlayer player)
	{
		return itemstack[index];
	}
	
	public String getQuestName()
	{
		return QuestName[index];
	}
	
	public int getQuestNumber()
	{
		return QuestNumber;
	}
}
