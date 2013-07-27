package aginsun.journey.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.journey.api.LevelKeeper;
import cpw.mods.fml.common.network.Player;

public class PacketLevel extends PacketJoL 
{
	private String username;
	private int level, SP;
	
	public PacketLevel() 
	{
		super(PacketType.LEVEL, false);
	}

	public PacketLevel(String username, int level, int SP) 
	{
		super(PacketType.LEVEL, false);
		this.username = username;
		this.level = level;
		this.SP = SP;
	}

	@Override
	public void readData(DataInputStream data) throws IOException 
	{
		this.username = data.readUTF();
		this.level = data.readInt();
		this.SP = data.readInt();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException 
	{
		dos.writeUTF(username);
		dos.writeInt(level);
		dos.writeInt(SP);
	}

	@Override
	public void execute(INetworkManager network, Player thePlayer) 
	{
		EntityPlayer player = (EntityPlayer) thePlayer;
		
		LevelKeeper.setLevel(player, level);
		LevelKeeper.setSP(player, SP);
	}	
}
