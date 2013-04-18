package aginsun.taleofkingdoms.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.TaleOfKingdoms;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class GuiPriceList extends GuiScreen
{
	private ChunkCoordinates chunkCoords;
	private EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
	private World world;
	
	public GuiPriceList(World world, int x, int y, int z)
	{
		chunkCoords = new ChunkCoordinates(x, y, z);
	}
	
	public GuiPriceList(World world, ChunkCoordinates chunkCoords)
	{
		this.world = world;
		this.chunkCoords = chunkCoords;
	}
	
	public void initGui()
	{
		buttonList.clear();
		buttonList.add(new GuiButton(1, width / 2, height / 2, 120, 20, "Back"));
		buttonList.add(new GuiButton(2, width / 2, height / 2 + 40, 120, 20, "Next page"));
		buttonList.add(new GuiButton(3, width / 2, height / 2 + 80, 120, 20, "Previous page"));
	}
	
	public void actionPerformed(GuiButton button)
	{
		if(button.id == 1)
		{
			mc.displayGuiScreen(null);
			player.openGui(TaleOfKingdoms.instance, 2, world, chunkCoords.posX, chunkCoords.posY, chunkCoords.posZ);
		}
	}
}
