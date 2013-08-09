package aginsun.journey.universal.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import aginsun.journey.client.guis.GuiQuest;
import aginsun.journey.server.api.ExperienceKeeper;
import aginsun.journey.server.api.GoldKeeper;
import aginsun.journey.server.api.QuestHandler;
import cpw.mods.fml.common.FMLCommonHandler;

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
	private String[][] questDiscription;
	
	public Quest(int QuestNumber)
	{
		this.QuestNumber = QuestNumber;
	}
	
	public Quest(int QuestNumber, int index, String[] StartLines, String[] EndLines, String[] ProgressLines, String[] QuestName, int[] RewardXP, int[] RewardGold, ItemStack[] itemstack, String[][] questDiscription)
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
		this.questDiscription = questDiscription;
	}
	
	public void update()
	{
		int QuestStatus = QuestHandler.instance().getQuestStatusClient(player, getQuestName());
		System.out.println("QuestStatus: " + QuestStatus);
		QuestType questtype = getQuestType();
		
		if(QuestStatus == 0 && (questtype == QuestType.HUNTING || questtype == QuestType.STUFF))
		{
			FMLCommonHandler.instance().showGuiScreen(new GuiQuest(getQuestName(), this, false));
			return;
		}
		if(QuestStatus == 0 && questtype == QuestType.GATHERING)
		{
			return;
		}
		if(QuestStatus == 1)
		{
			player.addChatMessage(this.questProgressLine(player));
			return;
		}
		if(QuestStatus == 2 && (questtype == QuestType.HUNTING || questtype == QuestType.STUFF))
		{
			FMLCommonHandler.instance().showGuiScreen(new GuiQuest(getQuestName(), this, true));
			return;
		}
		else
		{
			player.addChatMessage(this.standardLine());
			return;
		}
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
	
	public String[] getQuestDiscription()
	{
		return questDiscription[index];
	}
	
	public int getQuestNumber()
	{
		return QuestNumber;
	}
	
	public abstract QuestType getQuestType();
}
