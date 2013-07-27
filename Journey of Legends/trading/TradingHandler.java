package aginsun.journey.trading;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class TradingHandler 
{
	private HashMap<String, ArrayList<Trade>> tradeList = new HashMap<String, ArrayList<Trade>>();
	
	private static TradingHandler instance = new TradingHandler();
	
	public static TradingHandler getInstance()
	{
		return instance;
	}
	
	public void addTrade(EntityPlayer player, Trade trade)
	{
		ArrayList<Trade> tradeList = getTradesPlayer(player);
		tradeList.add(trade);
		setTradesPlayer(player, tradeList);
	}
	
	public void removeTrade(EntityPlayer player, Trade trade)
	{
		ArrayList<Trade> tradeList = getTradesPlayer(player);
		tradeList.remove(trade);
		setTradesPlayer(player, tradeList);
	}
	
	public void setTradesPlayer(EntityPlayer player, ArrayList<Trade> tradeList)
	{
		this.tradeList.put(player.username, tradeList);
	}
	
	public ArrayList<Trade> getTradesPlayer(EntityPlayer player)
	{
		return tradeList.get(player.username);
	}
	
	public Item getItemSelling(Trade trade)
	{
		return trade.selling;
	}
	
	public Item getItemBuying(Trade trade)
	{
		return trade.buying;
	}
	
	public int getGoldAmount(Trade trade)
	{
		return trade.gold;
	}
	
	public ArrayList<Trade> getAllTrades()
	{
		ArrayList<Trade> tradingList = new ArrayList<Trade>();

		for(ArrayList<Trade> list : tradeList.values())
		{
			for(Trade trade : list)
			{
				tradingList.add(trade);
			}
		}
		return tradingList;
	}
}
