package aginsun.journey.universal.handlers;

import java.util.HashMap;

import aginsun.journey.universal.quests.QuestAdventureStart;
import aginsun.journey.universal.quests.QuestGuildMaster;
import aginsun.journey.universal.utils.Quest;

public class QuestRegistry 
{
	private static HashMap<Integer, Quest> QuestMap = new HashMap<Integer, Quest>();
	
	public static void InitQuests()
	{
		registerQuest(new QuestGuildMaster(1, 0));
		registerQuest(new QuestGuildMaster(2, 1));
		registerQuest(new QuestGuildMaster(3, 2));
		registerQuest(new QuestAdventureStart(4, 0));
		registerQuest(new QuestGuildMaster(5, 3));
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
	
	public static HashMap<Integer, Quest> getMap()
	{
		return QuestMap;
	}
}
