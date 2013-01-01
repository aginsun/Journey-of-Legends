package aginsun.taleofkingdoms.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Use this class to save player-related data.
 * Register this class as a playerTracker like this:
 * 
 * @author Dries007
 *
 */
public class DataStorage implements IPlayerTracker 
{
	private boolean workSafe = true;
	private static DataStorage instance;
	
	private File folder = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).getChunkSaveLocation();
	private HashMap<String, NBTTagCompound> dataMap = new HashMap<String, NBTTagCompound>();
	
	public DataStorage()
	{
		instance = this;
	}
	
	public static DataStorage instance()
	{
		return instance;
	}
	
	public NBTTagCompound getData(String username)
	{
		username = username.toLowerCase().trim();
		return dataMap.get(username);
	}
	
	public void setData(String username, NBTTagCompound data)
	{
		username = username.toLowerCase().trim();
		dataMap.put(username, data);
		if(workSafe) saveData(username);
	}
	
	public void loadData(String username)
	{
		username = username.toLowerCase().trim();
		File playerfile = new File(folder, username + ".dat");
		if(playerfile.exists())
		{
			try
			{
				dataMap.put(username, CompressedStreamTools.readCompressed(new FileInputStream(playerfile)));
			}
			catch (FileNotFoundException e)
			{
				FMLLog.severe("Failed in reading file: " + playerfile.getName());
				e.printStackTrace();
			}
			catch (IOException e)
			{
				FMLLog.severe("Failed in reading file: " + playerfile.getName());
				e.printStackTrace();
			}
		}
		else
		{
			dataMap.put(username, new NBTTagCompound());
		}
		FMLLog.info("Loaded data " + username);
	}
	
	public void saveData(String username)
	{
		if(!folder.exists()) folder.mkdirs();
		
		username = username.toLowerCase().trim();
		File tempfile = new File(folder, username + "_tmp_.dat");
		File playerfile = new File(folder, username + ".dat");
		
		try
		{
			CompressedStreamTools.writeCompressed(dataMap.get(username), new FileOutputStream(tempfile));
		}
		catch (FileNotFoundException e)
		{
			FMLLog.severe("Failed in writing file: " + playerfile.getName());
			e.printStackTrace();
		}
		catch (IOException e)
		{
			FMLLog.severe("Failed in writing file: " + playerfile.getName());
			e.printStackTrace();
		}
		
		if (playerfile.exists()) playerfile.delete();

		tempfile.renameTo(playerfile);
		FMLLog.info("Saved data " + username);
	}

	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		loadData(player.username);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		saveData(player.username);
	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {}
}
