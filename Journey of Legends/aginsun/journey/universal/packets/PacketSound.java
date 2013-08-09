package aginsun.journey.universal.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;

public class PacketSound extends PacketJoL 
{
	private String username;
	private float volume, pitch;
	
	public PacketSound() 
	{
		super(PacketType.SOUND, false);
	}
	
	public PacketSound(String username, float volume, float pitch) 
	{
		super(PacketType.SOUND, false);
		this.username = username;
		this.volume = volume;
		this.pitch = pitch;
	}

	@Override
	public void readData(DataInputStream data) throws IOException
	{
		username = data.readUTF();
		volume = data.readFloat();
		pitch = data.readFloat();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException 
	{
		dos.writeUTF(username);
		dos.writeFloat(volume);
		dos.writeFloat(pitch);
	}

	@Override
	public void execute(INetworkManager network, Player par1player) 
	{
		EntityPlayer player = (EntityPlayer) par1player;
		
		World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
		
		world.playSoundAtEntity(player, "journey.startvoice", volume, pitch);
	}
}
