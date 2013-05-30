package aginsun.journey.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.journey.api.ExperienceKeeper;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketExperience extends PacketJoL
{
	private int Experience;
	private String username;
	public PacketExperience() 
	{
		super(PacketType.EXPERIENCE, false);
	}
	
	public PacketExperience(String username, int Experience)
	{
		super(PacketType.EXPERIENCE, false);
		this.username = username;
		this.Experience = Experience;
	}
	
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.Experience = data.readInt();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeInt(Experience);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		setWorthy(thePlayer);	
	}
	
	@SideOnly(Side.CLIENT)
	public void setWorthy(EntityPlayer player)
	{
		ExperienceKeeper.setExperience(player, Experience);
	}
}
