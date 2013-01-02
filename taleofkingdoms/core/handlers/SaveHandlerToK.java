package aginsun.taleofkingdoms.core.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.IPlayerTracker;
import aginsun.taleofkingdoms.core.DataStorage;
import aginsun.taleofkingdoms.core.GoldKeeper;

public class SaveHandlerToK implements IPlayerTracker
{
	public static int DataSaved = 1;
	private static SaveHandlerToK instance;
	public static int Worthy;
	private GoldKeeper gold;
	private NBTTagCompound data;
	
	public SaveHandlerToK()
	{
		instance = this;
	}
	
	@Override
	public void onPlayerLogin(EntityPlayer receiver)
	{
		getData(receiver);		
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
		data = DataStorage.instance().getData(player.username).getCompoundTag("ToKData");
		data.setBoolean("mute", true);
		data.setInteger("GoldTotal", gold.getGoldTotal(player));
		DataStorage.instance().setData(player.username, data);
	}
	
	public void getData(EntityPlayer player)
	{
		data = DataStorage.instance().getData(player.username).getCompoundTag("TokData");
		if(data.hasKey("GoldTotal"))
		{
			int i = data.getInteger("GoldTotal");
			gold.setGold(player, i);
		}
	}
}
