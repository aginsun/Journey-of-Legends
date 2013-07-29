package aginsun.journey.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.journey.api.LevelKeeper;
import aginsun.journey.api.StatKeeper;
import cpw.mods.fml.common.network.Player;

public class PacketStatChange extends PacketJoL
{
	private String username;
	private String stat;
	private int number;
	
	
	public PacketStatChange() 
	{
		super(PacketType.STATCHANGE, false);
	}

	public PacketStatChange(String username, String stat, int number) 
	{
		super(PacketType.STATCHANGE, false);
		this.username = username;
		this.stat = stat;
		this.number = number;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.stat = data.readUTF();
		this.number = data.readInt();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeUTF(stat);
		dos.writeInt(number);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		if(stat.equals("STR"))
			StatKeeper.addStrengthPoints(thePlayer, number);
		if(stat.equals("DEX"))
			StatKeeper.addDexPoints(thePlayer, number);
		if(stat.equals("INT"))
			StatKeeper.addIntPoints(thePlayer, number);
		if(stat.equals("LUK"))
			StatKeeper.addLukPoints(thePlayer, number);
		
		LevelKeeper.decreaseSP(thePlayer, 1);
	}
}
