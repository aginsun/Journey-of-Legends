package aginsun.taleofkingdoms.core.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import aginsun.taleofkingdoms.core.DataStorage;
import aginsun.taleofkingdoms.core.GoldKeeper;
import aginsun.taleofkingdoms.core.handlers.packets.PacketGold;
import aginsun.taleofkingdoms.core.handlers.packets.PacketToK;
import aginsun.taleofkingdoms.core.handlers.packets.PacketType;

public class SaveHandlerToK implements IPlayerTracker
{
	public static int DataSaved = 1;
	private static SaveHandlerToK instance;
	public static int Worthy;
	private GoldKeeper gold;
	private EntityPlayer player;
	private NBTTagCompound data;
	public Player par1player;
	
	public SaveHandlerToK()
	{
		instance = this;
	}
	
	@Override
	public void onPlayerLogin(EntityPlayer receiver)
	{
		this.par1player = (Player)receiver;
		getData(receiver);
		PacketDispatcher.sendPacketToPlayer(PacketType.populatePacket(new PacketGold(receiver.username, gold.getGoldTotal(receiver))), par1player);
	}
	
	@Override
	public void onPlayerLogout(EntityPlayer receiver)
	{
		SetData(receiver);
	}
	@Override
	public void onPlayerChangedDimension(EntityPlayer player){}
	@Override
	public void onPlayerRespawn(EntityPlayer player){}
	
	public void SetData(EntityPlayer player)
	{
		data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
		//data = DataStorage.instance().getData(player.username).getCompoundTag("ToKData");
		data.setBoolean("mute", true);
		data.setInteger("GoldTotal", gold.getGoldTotal(player));
		player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, data);
		//DataStorage.instance().setData(player.username, data);
	}
	
	public void getData(EntityPlayer player)
	{
		if(player != null)
		{
			data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
			//data = DataStorage.instance().getData(player.username).getCompoundTag("TokData");
			if(data.hasKey("GoldTotal"))
			{
				int i = data.getInteger("GoldTotal");
				gold.setGold(player, i);
			}
		}
	}
}
