package aginsun.taleofkingdoms.core.handlers.commands;

import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
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
			if(args.length >= 2)
			{
				int i = parseIntWithMin(sender, args[1], 1);
				stats.setStrengthPoints((EntityPlayer)sender, i);
			}
		}
	}

}
