package aginsun.journey.server.api;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import aginsun.journey.universal.packets.PacketStatsClient;
import aginsun.journey.universal.packets.PacketType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * WIP: if you want add stats to a player
 * @author Aginsun
 */
public class StatKeeper 
{
	public static HashMap<String, Integer> StrengthMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> DexeterityMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> IntelligenceMap = new HashMap<String, Integer>();
	public static HashMap<String, Integer> LuckMap = new HashMap<String, Integer>();
	
	public static int getStrengthPoints(EntityPlayer player)
	{
		if(!StrengthMap.containsKey(player.username))
		{
			return 4;
		}
		else if(StrengthMap.get(player.username) == 0)
		{
			return 4;
		}
		else
		{
			return StrengthMap.get(player.username);
		}
	}
	public static int getDexerityPoints(EntityPlayer player)
	{
		if(!DexeterityMap.containsKey(player.username))
		{
			return 4;
		}
		else if (DexeterityMap.get(player.username) == 0)
		{
			return 4;
		}
		else
		{
			return DexeterityMap.get(player.username);
		}
	}
	public static int getIntelligencePoints(EntityPlayer player)
	{
		if(!IntelligenceMap.containsKey(player.username))
		{
			return 4;
		}
		else if(IntelligenceMap.get(player.username) == 0)
		{
			return 4;
		}
		else
		{
			return IntelligenceMap.get(player.username);
		}
	}
	public static int getLuckPoints(EntityPlayer player)
	{
		if(!LuckMap.containsKey(player.username))
		{
			return 4;
		}
		else if(LuckMap.get(player.username) == 0)
		{
			return 4;
		}
		else
		{
			return LuckMap.get(player.username);
		}
	}
	
	public static void setStrengthPoints(EntityPlayer player, int amount)
	{
		StrengthMap.put(player.username, amount);
	}
	public static void setDexerityPoints(EntityPlayer player, int amount)
	{
		DexeterityMap.put(player.username, amount);
	}
	public static void setIntelligencePoints(EntityPlayer player, int amount)
	{
		IntelligenceMap.put(player.username, amount);
	}
	public static void setLuckPoints(EntityPlayer player, int amount)
	{
		LuckMap.put(player.username, amount);
	}
	
	public static void addStrengthPoints(EntityPlayer player, int amount) 
	{
		int i = getStrengthPoints(player);
		i += amount;
		setStrengthPoints(player, i);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketStatsClient(player.username, StatKeeper.getStrengthPoints(player), StatKeeper.getDexerityPoints(player), StatKeeper.getIntelligencePoints(player), StatKeeper.getLuckPoints(player))), (Player)player);
	}
	public static void addDexPoints(EntityPlayer player, int amount)
	{
		int j = getDexerityPoints(player);
		j += amount;
		setDexerityPoints(player, j);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketStatsClient(player.username, StatKeeper.getStrengthPoints(player), StatKeeper.getDexerityPoints(player), StatKeeper.getIntelligencePoints(player), StatKeeper.getLuckPoints(player))), (Player)player);
	}
	public static void addIntPoints(EntityPlayer player, int amount)
	{
		int j = getIntelligencePoints(player);
		j += amount;
		setIntelligencePoints(player, j);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketStatsClient(player.username, StatKeeper.getStrengthPoints(player), StatKeeper.getDexerityPoints(player), StatKeeper.getIntelligencePoints(player), StatKeeper.getLuckPoints(player))), (Player)player);
	}	
	public static void addLukPoints(EntityPlayer player, int amount)
	{
		int j = getLuckPoints(player);
		j += amount;
		setLuckPoints(player, j);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketStatsClient(player.username, StatKeeper.getStrengthPoints(player), StatKeeper.getDexerityPoints(player), StatKeeper.getIntelligencePoints(player), StatKeeper.getLuckPoints(player))), (Player)player);
	}
}
