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
	public int QuestNumber;
	
	public Quest(int QuestNumber)
	{
		this.QuestNumber = QuestNumber;
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
		
	public abstract String questStartLines(EntityPlayer player);
	
	public abstract String questEndLines(EntityPlayer player);

	public abstract String questProgressLine(EntityPlayer player);
	
	public abstract int questEndRewardXP(EntityPlayer player);
	
	public abstract int questEndRewardGold(EntityPlayer player);
	
	public abstract ItemStack questEndRewardItemStacks(EntityPlayer player);
	
	public abstract String getQuestName();
}