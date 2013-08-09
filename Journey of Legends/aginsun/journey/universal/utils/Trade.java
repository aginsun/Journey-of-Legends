package aginsun.journey.universal.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class Trade
{
	public Item selling, buying;
	public int gold;
	public EntityPlayer username;
	public EnumType enumType;
	
	public Trade(Item selling, Item buying, int gold, EnumType enumType, EntityPlayer username)
	{
		this.selling = selling;
		this.buying = buying;
		this.gold = gold;
		this.enumType = enumType;
		this.username = username;
	}
	
	public enum EnumType
	{
		SELLING, BUYING;
	}
}
