package aginsun.taleofkingdoms.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.HunterKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

public class PacketGold extends PacketToK
{
	private String username;
	private int GoldValue;
	private float Worthy;
	private boolean HunterStatus;
	
	public PacketGold() 
	{
		super(PacketType.GOLD, false);
	}
	
	public PacketGold(String username, int GoldValue, float Worthy, boolean HunterStatus)
	{
		super(PacketType.GOLD, false);
		this.username = username;
		this.GoldValue = GoldValue;
		this.Worthy = Worthy;
		this.HunterStatus = HunterStatus;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.GoldValue = data.readInt();
		this.Worthy = data.readFloat();
		this.HunterStatus = data.readBoolean();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeInt(GoldValue);
		dos.writeFloat(Worthy);
		dos.writeBoolean(HunterStatus);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		setValues(thePlayer);
		
	}
	
	@SideOnly(Side.CLIENT)
	public void setValues(EntityPlayer player)
	{
		GoldKeeper.setGold(player, GoldValue);
		WorthyKeeper.setWorthy(player, Worthy);
		HunterKeeper.setHunterStatus(player, HunterStatus);
	}
}
