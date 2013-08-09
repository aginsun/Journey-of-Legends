package aginsun.journey.universal.packets;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import aginsun.journey.universal.utils.Utils;

public enum PacketType 
{
	GOLD(PacketGold.class),
	EXPERIENCE(PacketExperience.class),
	STATS(PacketStatsClient.class),
	STATCHANGE(PacketStatChange.class),
	RACE(PacketRace.class), 
	QUESTDATA(PacketQuestData.class),
	QUESTDATACLIENT(PacketQuestDataClient.class),
	LEVEL(PacketLevel.class),
	SOUND(PacketSound.class);
	
	private Class<? extends PacketJoL> clazz;
	
	PacketType(Class<? extends PacketJoL> clazz)
	{
		this.clazz = clazz;
	}
	
    public static PacketJoL buildPacket(byte[] data) {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        int selector = bis.read();
        DataInputStream dis = new DataInputStream(bis);

        PacketJoL packet = null;

        try {
            packet = values()[selector].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        packet.readPopulate(dis);

        return packet;
    }

    public static PacketJoL buildPacket(PacketType type) {

        PacketJoL packet = null;

        try {
            packet = values()[type.ordinal()].clazz.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return packet;
    }

    public static Packet populatePacket(PacketJoL packetToK) {

        byte[] data = packetToK.populate();

        Packet250CustomPayload packet250 = new Packet250CustomPayload();
        packet250.channel = Utils.modID;
        packet250.data = data;
        packet250.length = data.length;
        packet250.isChunkDataPacket = packetToK.isChunkDataPacket;

        return packet250;
    }
}
