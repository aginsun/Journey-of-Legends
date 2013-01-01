package aginsun.taleofkingdoms.core.handlers;

import aginsun.taleofkingdoms.core.DataStorage;
import aginsun.taleofkingdoms.core.GoldKeeper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;


public class SaveHandlerToK implements IPlayerTracker
{
	public static int DataSaved = 1;
	public static int Worthy;
	private GoldKeeper gold;
	private NBTTagCompound data;
	public File playerdata;
	
	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		try
		{
			playerdata = new File(player.username + ".dat");
			if(!playerdata.exists())
			{
				playerdata.createNewFile();
				WriteToNBT();
			}
			ReadFromNBT();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void onPlayerLogout(EntityPlayer player)
	{
		try
		{
			playerdata = new File(player.username + ".dat");
			WriteToNBT();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public void onPlayerChangedDimension(EntityPlayer player){}
	@Override
	public void onPlayerRespawn(EntityPlayer player){}
	
	public void WriteToNBT()
	{
        try
        {
        	if(playerdata != null)
        	{
        		FileOutputStream fileoutputstream = new FileOutputStream(playerdata);
        		NBTTagCompound nbt = new NBTTagCompound();
        	
        		nbt.setInteger("Worthy", Worthy);
        		nbt.setInteger("GoldTotal", gold.GoldTotal);
        	
        		CompressedStreamTools.writeCompressed(nbt, fileoutputstream);
        		fileoutputstream.close();
        	}
        }catch(Exception e){
        	e.printStackTrace();
        }
	}
	
	public void ReadFromNBT()
	{
		try
		{
			if(playerdata != null)
			{
				FileInputStream fileinputstream = new FileInputStream(playerdata);
				NBTTagCompound nbt = CompressedStreamTools.readCompressed(fileinputstream);
				if(nbt.hasKey("Worthy"))
				{
					Worthy = nbt.getInteger("Worthy");
				}
				if(nbt.hasKey("GoldTotal"))
				{
					gold.GoldTotal = nbt.getInteger("GoldTotal");
				}
			}
		}catch(Exception e){
				e.printStackTrace();
		}
	}
}
