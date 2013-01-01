package aginsun.taleofkingdoms.core.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;

/** File to save all the player data
 *  Thanks to Dries007 for showing how to use
 *  the IPlayerTracker class. 
 *  He deserves all the credits!
 */
import cpw.mods.fml.common.IPlayerTracker;
import aginsun.taleofkingdoms.core.GoldKeeper;

public class SaveHandlerToK implements IPlayerTracker
{
	public static int DataSaved = 1;
	public static int Worthy;
	private GoldKeeper gold;
	private NBTTagCompound data;
	
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
		data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
		data.setBoolean("mute", true);
		data.setInteger("GoldTotal", gold.GoldTotal);
		player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, data);
	}
	
	public void getData(EntityPlayer player)
	{
		data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
		if(data.hasKey("GoldTotal"))
		{
			gold.GoldTotal = data.getInteger("GoldTotal");
		}
	}
}
