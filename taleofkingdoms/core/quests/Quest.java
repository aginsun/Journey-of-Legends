package aginsun.taleofkingdoms.core.quests;

import net.minecraft.entity.player.EntityPlayer;

public interface Quest
{
	public String QuestStartLines(int number);
	
	public String QuestStartLines(EntityPlayer player, int number);
	
	public String QuestEndLines(int number);
	
	public String QuestEndLines(EntityPlayer player, int number);
	
	public void QuestEndReward(EntityPlayer player, int number);
}
