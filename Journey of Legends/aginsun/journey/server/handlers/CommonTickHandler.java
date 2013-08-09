package aginsun.journey.server.handlers;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler
{	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if(type.equals(EnumSet.of(TickType.SERVER)))
		{
			onTickInGame();
		}
	}

    public EnumSet ticks()
	{
    	return EnumSet.of(TickType.SERVER);
	}

   public String getLabel()
   {
	   return null;
   }
	    
   public void onTickInGame()
   {

   }
}
