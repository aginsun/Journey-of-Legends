package aginsun.journey.core.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;

public class WorldSaveToKHandler
{
	private static World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
	private static CommonTickHandler x;
	public static File file = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).getChunkSaveLocation();
	
	public static void writeData()
	{
		try
		{
			File file = new File(new StringBuilder().append(FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).getChunkSaveLocation()).append("/").append("KingdomModSave.dat").toString());
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileOutputStream fileoutputstream = new FileOutputStream(file);
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			
			nbttagcompound.setBoolean("BuildingsCreated", x.buildingsCreated);

			CompressedStreamTools.writeCompressed(nbttagcompound, fileoutputstream);
			fileoutputstream.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public static void readData()
	{
	    try
        {
			File file = new File(new StringBuilder().append(FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).getChunkSaveLocation()).append("/").append("KingdomModSave.dat").toString());
        	if(!file.exists())
        	{
        		return;
        	}
        
        	FileInputStream fileinputstream = new FileInputStream(file);
        	NBTTagCompound nbt = CompressedStreamTools.readCompressed(fileinputstream);
        	
        	if(nbt.hasKey("BuildingsCreated"))
        	{
        		x.buildingsCreated = nbt.getBoolean("BuildingsCreated");
        	}
        	
    		fileinputstream.close();
        }
	    catch(Exception exception)
	    {
	    	exception.printStackTrace();
	    }
	}
}
