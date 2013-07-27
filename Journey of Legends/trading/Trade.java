package aginsun.journey.trading;

import net.minecraft.item.Item;

public class Trade
{
	public Item selling, buying;
	public int gold;
	public EnumType enumType;
	
	public Trade(Item selling, Item buying, int gold, EnumType enumType)
	{
		this.selling = selling;
		this.buying = buying;
		this.gold = gold;
		this.enumType = enumType;
	}
	
	public enum EnumType
	{
		SELLING, BUYING;
	}
}
