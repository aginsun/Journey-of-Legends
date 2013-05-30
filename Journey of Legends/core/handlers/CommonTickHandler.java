package aginsun.journey.core.handlers;

import java.util.EnumSet;

import net.minecraft.world.World;
import aginsun.journey.core.ConfigFileJoL;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class CommonTickHandler implements ITickHandler
{
	private World world;
	boolean dataRead;
	public ConfigFileJoL x;
	public WorldSaveToKHandler td;
	public static int CreateGuild;
	public static boolean ks;
	
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
