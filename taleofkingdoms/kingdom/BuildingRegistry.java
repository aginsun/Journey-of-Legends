package aginsun.taleofkingdoms.kingdom;

import java.util.HashMap;

public class BuildingRegistry 
{
	private static HashMap<Integer, KingdomStructure> BuildingList = new HashMap<Integer, KingdomStructure>();
	
	public static void RegisterBuildings()
	{
		registerBuilding(1, new RandomBuilding(0, 0, 0));
	}
	
	private static void registerBuilding(int index, KingdomStructure kingdom)
	{
		BuildingList.put(index, kingdom);
	}
	
	public static KingdomStructure getStructure(int index)
	{
		if(BuildingList.containsKey(index))
			return BuildingList.get(index);
		return null;
	}
}
