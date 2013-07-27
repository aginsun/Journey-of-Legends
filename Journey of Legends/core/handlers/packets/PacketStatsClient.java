package aginsun.journey.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.journey.api.StatKeeper;
import cpw.mods.fml.common.network.Player;

public class PacketStatsClient extends PacketJoL
{
	private String username;
	private int strength;
	private int dexterity;
	private int intelligence;
	private int luck;
	
	public PacketStatsClient() 
	{
		super(PacketType.STATS, false);
	}
	
	public PacketStatsClient(String username, int strength, int dexterity, int intelligence, int luck)
	{
		super(PacketType.STATS, false);
		this.username = username;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.luck = luck;
	}
	
	@Override
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.strength = data.readInt();
		this.dexterity = data.readInt();
		this.intelligence = data.readInt();
		this.luck = data.readInt();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeInt(strength);
		dos.writeInt(dexterity);
		dos.writeInt(intelligence);
		dos.writeInt(luck);
	}
	
	public void execute(INetworkManager network, Player thePlayer)
	{
		EntityPlayer player = (EntityPlayer)thePlayer;
		
		StatKeeper.setStrengthPoints(player, strength);
		StatKeeper.setDexerityPoints(player, dexterity);
		StatKeeper.setIntelligencePoints(player, intelligence);
		StatKeeper.setLuckPoints(player, luck);
	}
}
