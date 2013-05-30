package aginsun.journey.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.journey.core.handlers.RaceKeeper;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketRace extends PacketJoL
{
	public String race;
	public String username;
	
	public PacketRace() 
	{
		super(PacketType.RACE, false);
	}
	
	public PacketRace(String username, String race)
	{
		super(PacketType.RACE, false);
		this.username = username;
		this.race = race;
	}
	
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.race = data.readUTF();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeUTF(race);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		setValues(thePlayer);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void setValues(EntityPlayer player)
	{
		RaceKeeper.setClass(player, race);
	}
}
