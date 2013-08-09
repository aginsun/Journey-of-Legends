package aginsun.journey.universal.handlers;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import aginsun.journey.universal.packets.PacketJoL;
import aginsun.journey.universal.packets.PacketType;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
	{
		PacketJoL packetToK = PacketType.buildPacket(packet.data);
		packetToK.execute(manager, player);
	}

}
