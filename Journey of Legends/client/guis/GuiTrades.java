package aginsun.journey.client.guis;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import aginsun.journey.trading.Trade;
import aginsun.journey.trading.TradingHandler;
import aginsun.journey.trading.Trade.EnumType;

public class GuiTrades extends GuiScreen
{
	private int pages;
	ArrayList<Trade> tradeList;
	public GuiTrades()
	{
		tradeList = TradingHandler.getInstance().getAllTrades();
		pages = Math.round(tradeList.size() / 8);
	}
	
	public void initGui()
	{
		buttonList.clear();
				
		buttonList.add(new GuiButton(1, width / 2, height / 2, 120, 20, "Next"));
		buttonList.add(new GuiButton(2, width / 2, height / 2, 120, 20, "previous"));
		
		
		for(int i = pages * 8; i < tradeList.size(); i++)
		{
			int trade = i + 2;
			if(tradeList.get(i).enumType == EnumType.BUYING)
				buttonList.add(new GuiButton(trade, width / 2, height / 2, 120, 20, "Buy"));
			else if(tradeList.get(i).enumType == EnumType.SELLING)
				buttonList.add(new GuiButton(trade, width / 2, height / 2, 120, 20, "Sell"));
		}
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
			pages++;
		else if(guibutton.id == 2)
			pages--;
		else
		{
			int i = guibutton.id - 2;
			Trade trade = tradeList.get(i);
		}
	}
	
	public void drawScreen(int i, int j, float f)
	{
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	char c = '\377';
    	char c1 = '\377';
    	mc.renderEngine.bindTexture("/aginsun/textures/crafting.png");
    	int i1 = (width - c) / 2;
    	drawTexturedModalRect(i1, 0, 0, 0, c, c1);
    	
		super.drawScreen(i, j, f);
	}
}
