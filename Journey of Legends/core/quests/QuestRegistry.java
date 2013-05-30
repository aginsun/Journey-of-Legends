package aginsun.journey.core.quests;

import java.awt.List;
import java.util.Map;
import java.util.TreeMap;

public class QuestRegistry 
{
	private static Map<Integer, Quest> QuestMap = new TreeMap();
	
	public static void InitQuests()
	{
		registerQuest(new QuestGuildMaster(1, 0));
		registerQuest(new QuestGuildMaster(2, 1));
		registerQuest(new QuestGuildMaster(3, 2));
		//registerQuest(new QuestFarmer(4, 0));
		//registerQuest(new QuestFarmer(5, 1));
		//registerQuest(new QuestFarmer(6, 2));
	}
	
	public static void registerQuest(Quest quest)
	{
		QuestMap.put(quest.getQuestNumber(), quest);
	}
	
	public static Quest getQuest(int QuestNumber)
	{
		return QuestMap.get(QuestNumber);
	}
	
	public static Map<Integer, Quest> getMap()
	{
		return QuestMap;
	}
}
