package aginsun.taleofkingdoms.core;

public class GoldKeeper
{
	public static int GoldTotal;
	
	public static int getGoldTotal()
	{
		return GoldTotal;
	}
	
	public static void addGold()
	{
		GoldTotal++;
	}
	
	public static void addGold(int i)
	{
		GoldTotal += i;
	}
	
	public static void DecreaseGold() //Might not be a use for this, but it is there in case people want to use it!
	{
		if(GoldTotal >= 1)
		{
			GoldTotal--;
		}
	}
	
	public static void DecreaseGold(int i)
	{
		if(GoldTotal >= i && i > 0)
		{
			GoldTotal -= i;
		}
		else if(GoldTotal < i && i > 0)
		{
			//Do nothing?
		}
		else
		{
			Exception e = new Exception();			
			e.printStackTrace();
		}		
	}
	
	public static int PriceItem(String s)
	{
		if(s.equals("tile.dirt"))
		{
			return 1;
		}
		
		return 0;
	}
}
