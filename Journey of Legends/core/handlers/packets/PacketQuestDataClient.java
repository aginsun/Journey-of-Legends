package aginsun.journey.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.journey.api.QuestHandler;
import cpw.mods.fml.common.network.Player;

public class PacketQuestDataClient extends PacketJoL
{
	private String username;
	private String questName;
	private int questStatus;
	
	public PacketQuestDataClient() 
	{
		super(PacketType.QUESTDATACLIENT, false);
	}
	
	public PacketQuestDataClient(String username, String questName, int questStatus)
	{
		super(PacketType.QUESTDATACLIENT, false);
		this.username = username;
		this.questName = questName;
		this.questStatus = questStatus;
	}
	
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.questName = data.readUTF();
		this.questStatus = data.readInt();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeUTF(questName);
		dos.writeInt(questStatus);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		QuestHandler.instance().setQuestStatusClient(thePlayer, questName, questStatus);
	}
}