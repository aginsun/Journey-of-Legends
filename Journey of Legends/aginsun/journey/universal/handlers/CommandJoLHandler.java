package aginsun.journey.universal.handlers;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import aginsun.journey.server.api.GoldKeeper;
import aginsun.journey.server.api.StatKeeper;
import aginsun.journey.server.handlers.PartyHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommandJoLHandler extends CommandBase
{
	private GoldKeeper gold;
	private StatKeeper stats;
	private World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
	
	@Override
    public int getRequiredPermissionLevel()
    {
        return 1;
    }
	
	@Override
	public String getCommandName() 
	{
		return "JourneyofLegends";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) 
	{
		if(!(sender instanceof EntityPlayer))
		{
			System.out.println("[Journey of Legends] Command only executeable by players");
			return;
		}
		
		if(args[0].matches("party"))
		{
			if(args[1].matches("create"))
			{
				if(args.length >= 4)
				{
					String partyName = args[2];
					String color = args[3];
					Color partyColor;
					if(color.equals("red"))
						partyColor = Color.RED;
					else if(color.equals("blue"))
						partyColor = Color.BLUE;
					else if(color.equals("green"))
						partyColor = Color.GREEN;
					else
						partyColor = Color.BLACK;
					List<String> usernames = new ArrayList<String>();
					usernames.add(sender.getCommandSenderName());
					PartyHandler.getInstance().makeParty(partyName, usernames, partyColor, sender.getCommandSenderName());
				}
				else{}
					//sender.sendChatToPlayer("Please enter the partyname.");
			}
			else if(args[1].matches("disband"))
			{
				if(PartyHandler.getInstance().isPlayerInParty(sender.getCommandSenderName()))
				{
					if(args.length >= 3)
						PartyHandler.getInstance().disbandParty(args[2]);
					else{}
						//sender.sendChatToPlayer("Please enter the partyname.");
				}
				else{}
					//sender.sendChatToPlayer("You have to be in a party to disband it.");
			}
			else if(args[1].matches("addPlayer"))
			{
				if(args.length >= 4)
					PartyHandler.getInstance().addPlayerToParty(args[3], args[2]);
				else{}
					//sender.sendChatToPlayer("Please enter the partyname and/or username");
			}
			else if(args[1].matches("removePlayer"))
			{
				if(args.length >= 4)
					PartyHandler.getInstance().removePlayerFromParty(args[3], args[2]);
				else{}
					//sender.sendChatToPlayer("Please enter the partyname and/or username");
			}
		}
		else if(args[0].matches("help"))
		{
			//sender.sendChatToPlayer("================================");
			//sender.sendChatToPlayer("Journey of Legends Command help");
			//sender.sendChatToPlayer("================================");
			//sender.sendChatToPlayer("/JourneyofLegends create <partyName> <color>");
			//sender.sendChatToPlayer("/JourneyofLegends disband <partyName>");
			//sender.sendChatToPlayer("/JourneyofLegends addPlayer <partyName> <usernamePlayer>");
			//sender.sendChatToPlayer("/JourneyofLegends removePlayer <partyName> <usernamePlayer>");
		}
	}

	@Override
	public String getCommandUsage(ICommandSender icommandsender) {
		return null;
	}
}
