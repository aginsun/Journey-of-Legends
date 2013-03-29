package aginsun.taleofkingdoms.core.handlers;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import aginsun.taleofkingdoms.core.handlers.packets.PacketToK;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
	{
		PacketToK packetToK = PacketType.buildPacket(packet.data);
		packetToK.execute(manager, player);
	}

}
