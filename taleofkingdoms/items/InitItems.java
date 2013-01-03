package aginsun.taleofkingdoms.items;

import net.minecraft.item.Item;

public class InitItems 
{
	public static Item ItemCoins;
	
	public static void Init()
	{
		ItemCoins = new ItemCoins(17655).setItemName("coins");
	}
}
