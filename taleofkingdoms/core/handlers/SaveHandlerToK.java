package aginsun.taleofkingdoms.core.handlers;

import aginsun.taleofkingdoms.core.GoldKeeper;
import cpw.mods.fml.common.FMLCommonHandler;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;


public class SaveHandlerToK
{
	public static int DataSaved = 1;//TODO: change this up (placeholder)
	public static int Worthy = 10;
	
	public static void WriteToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("DataSaved", DataSaved);
		nbt.setInteger("GoldTotal", GoldKeeper.GoldTotal);
	}
	public static void ReadFromNBT(NBTTagCompound nbt)
	{
		if(nbt.hasKey("GoldTotal"))
		{
			GoldKeeper.GoldTotal = nbt.getInteger("GoldTotal");
		}
		if(nbt.hasKey("DataSaved"))
		{
			DataSaved = nbt.getInteger("DataSaved");
		}
	}
}
