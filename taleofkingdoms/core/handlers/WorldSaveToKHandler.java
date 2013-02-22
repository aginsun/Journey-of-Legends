package aginsun.taleofkingdoms.core.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class WorldSaveToKHandler
{
	private static World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
	private static CommonTickHandler x;
	public static int tdd;
	public static File file = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0).getChunkSaveLocation();
	
	public static void writeData()
	{
        try
        {
            File SaveFile = new File(file, "KingdomModSave.dat"); 
        	if(!SaveFile.exists())
        	{
        		SaveFile.createNewFile();
        	}
        	FileOutputStream fileoutputstream = new FileOutputStream(SaveFile);
        	NBTTagCompound nbt = new NBTTagCompound();
        	
        	nbt.setInteger("WorldGenGuild", x.tdd);
        	
        	CompressedStreamTools.writeCompressed(nbt, fileoutputstream);
        	fileoutputstream.close();
        }
        catch(Exception e){}
	}
	
	public static void readData()
	{
	    try
        {
	    	File SaveFile = new File(file, "KingdomModSave.dat");
        	if(!SaveFile.exists())
        	{
        		return;
        	}
        
        	FileInputStream fileinputstream = new FileInputStream(SaveFile);
        	NBTTagCompound nbt = CompressedStreamTools.readCompressed(fileinputstream);
        	
        	if(nbt.hasKey("WorldGenGuild"))
        	{
        		x.tdd = nbt.getInteger("WorldGenGuild");
        	}
        	
    		fileinputstream.close();
        }
	    catch(Exception exception)
	    {
	    	exception.printStackTrace();
	    }
	}
}
