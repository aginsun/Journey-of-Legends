package aginsun.taleofkingdoms.core.handlers.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import aginsun.taleofkingdoms.core.goldSystem.LevelKeeper;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketCurrentLevel extends PacketToK
{
	private String username;
	private int CurrentLevel;
	private int LevelUps;
	private int LevelPoints;
	
	public PacketCurrentLevel() 
	{
		super(PacketType.LEVEL, false);
	}
	
	public PacketCurrentLevel(String username, int CurrentLevel, int LevelUps, int LevelPoints)
	{
		super(PacketType.LEVEL, false);
		this.username = username;
		this.CurrentLevel = CurrentLevel;
		this.LevelUps = LevelUps;
		this.LevelPoints = LevelPoints;
	}
	
	public void readData(DataInputStream data) throws IOException
	{
		this.username = data.readUTF();
		this.CurrentLevel = data.readInt();
		this.LevelUps = data.readInt();
		this.LevelPoints = data.readInt();
	}
	
	public void writeData(DataOutputStream dos) throws IOException
	{
		dos.writeUTF(username);
		dos.writeInt(CurrentLevel);
		dos.writeInt(LevelUps);
		dos.writeInt(LevelPoints);
	}
	
	public void execute(INetworkManager network, Player player)
	{
		EntityPlayer thePlayer = (EntityPlayer)player;
		
		setValues(thePlayer);
	}
	
	@SideOnly(Side.CLIENT)
	public void setValues(EntityPlayer player)
	{
		LevelKeeper.setCurrentLevel(player, CurrentLevel);
		LevelKeeper.setLevelUps(player, LevelUps);
		LevelKeeper.setLevelPoints(player, LevelPoints);
	}
}
