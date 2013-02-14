package aginsun.taleofkingdoms.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketStats extends PacketToK
{
	private String username;
	private int strength;
	private int dexterity;
	private int intelligence;
	private int luck;
	private int level;
	public PacketStats() 
	{
		super(PacketType.STATS, false);
	}
	
	public PacketStats(String username, int strength, int dexterity, int intelligence, int luck, int level)
	{
		super(PacketType.STATS, false);
		this.username = username;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.luck = luck;
		this.level = level;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.strength = data.readInt();
		this.dexterity = data.readInt();
		this.intelligence = data.readInt();
		this.luck = data.readInt();
		this.level = data.readInt();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeInt(strength);
		dos.writeInt(dexterity);
		dos.writeInt(intelligence);
		dos.writeInt(luck);
		dos.writeInt(level);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		setStats(thePlayer);
	}
	
	@SideOnly(Side.CLIENT)
	public void setStats(EntityPlayer player)
	{
		StatKeeper.setStatPoints(player, "STR", strength);
		StatKeeper.setStatPoints(player, "DEX", dexterity);
		StatKeeper.setStatPoints(player, "INT", intelligence);
		StatKeeper.setStatPoints(player, "LUK", luck);
		StatKeeper.setStatPoints(player, "LVL", level);
	}
}
