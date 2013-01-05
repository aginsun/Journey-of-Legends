package aginsun.taleofkingdoms.core.handlers.commands;

import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandTaleofKingdoms extends CommandBase
{
	private GoldKeeper gold;
	@Override
	public String getCommandName() 
	{
		return "taleofkingdoms";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] arguments) 
	{
	}

}
