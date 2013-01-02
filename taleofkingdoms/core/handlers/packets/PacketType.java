package aginsun.taleofkingdoms.core.handlers.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.lang.ref.Reference;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;

public enum PacketType 
{
	TILE(PacketTileUpdate.class);
	
	private Class<? extends PacketToK> clazz;
	
	PacketType(Class<? extends PacketToK> clazz)
	{
		this.clazz = clazz;
	}
	
    public static PacketToK buildPacket(byte[] data) {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        PacketToK packet = null;

        try {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }

    public static PacketToK buildPacket(PacketType type) {

        PacketToK packet = null;

        try {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(PacketToK packetEE) {

        byte[] data = packetEE.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = "TaleOfKingdoms";
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetEE.isChunkDataPacket;

        return packet250;
    }
}
