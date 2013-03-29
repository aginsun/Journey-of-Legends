package aginsun.taleofkingdoms.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketWorthy extends PacketToK
{
	private float Worthy;
	private String username;
	public PacketWorthy() 
	{
		super(PacketType.WORTHY, false);
	}
	
	public PacketWorthy(String username, float Worthy)
	{
		super(PacketType.WORTHY, false);
		this.username = username;
		this.Worthy = Worthy;
	}
	
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.Worthy = data.readFloat();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeFloat(Worthy);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		setWorthy(thePlayer);	
	}
	
	@SideOnly(Side.CLIENT)
	public void setWorthy(EntityPlayer player)
	{
		WorthyKeeper.setWorthy(player, Worthy);
	}
}
