package aginsun.taleofkingdoms.core.handlers.commands;

import cpw.mods.fml.common.FMLCommonHandler;
import aginsun.taleofkingdoms.client.guis.GuiRaceSelect;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.RaceKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandTaleofKingdoms extends CommandBase
{
	private GoldKeeper gold;
	private StatKeeper stats;
	
	@Override
	public String getCommandName() 
	{
		return "taleofkingdoms";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) 
	{
		if(args[0].matches("stats"))
		{
			if(args[1].matches("Level"))
			{
				if(args.length >= 3)
				{
					int i = parseIntWithMin(sender, args[2], 1);
					stats.setLevel((EntityPlayer)sender, i);
				}
			}
			if(args[1].matches("Strength"))
			{
				if(args.length >= 3)
				{
					int i = parseIntWithMin(sender, args[2], 1);
					stats.setStrengthPoints((EntityPlayer)sender, i);
				}
			}
			if(args[1].matches("Dexerity"))
			{
				if(args.length >= 3)
				{
					int i = parseIntWithMin(sender, args[2], 1);
					stats.setDexerityPoints((EntityPlayer)sender, i);
				}
			}
			if(args[1].matches("Intelligence"))
			{
				if(args.length >= 3)
				{
					int i = parseIntWithMin(sender, args[2], 1);
					stats.setIntelligencePoints((EntityPlayer)sender, i);
				}
			}
		}
		
		if(args[0].matches("Reset"))
		{
			RaceKeeper.Race.clear();
		}
		if(args[0].matches("guis"))
		{
			if(args[1].matches("Race"))
			{
				FMLCommonHandler.instance().showGuiScreen(new GuiRaceSelect());
			}
		}
	}

}
