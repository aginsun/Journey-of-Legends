package aginsun.taleofkingdoms.client.guis;

import java.awt.Color;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import aginsun.taleofkingdoms.api.GoldKeeper;
import aginsun.taleofkingdoms.entities.TileEntityKingdom;
import aginsun.taleofkingdoms.inventory.ContainerKingdom;
import aginsun.taleofkingdoms.worldgen.WorldGenBuildingsPart1;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;

public class GuiKingdom extends GuiContainer
{
	private static EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
	
	private GuiButton buttonYes;
	private GuiButton buttonNo;
	private GuiButton buttonSelectPart;
	private GuiButton buttonPriceList;
	private GuiButton buttonSelectBuild;
	private static TileEntityKingdom tileEntity;
	private int BuildingNumberSelected;
	private static ChunkCoordinates chunkCoords;
	private static World world;
	
	public GuiKingdom(InventoryPlayer inventory, TileEntityKingdom tileEntity)
	{
		super(new ContainerKingdom(tileEntity, inventory));
		this.tileEntity = tileEntity;
		world = this.tileEntity.worldObj;
		chunkCoords = new ChunkCoordinates(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
	}
	
	@Override
	public void initGui() 
	{
		super.initGui();	
		
		xSize = 256 * 3;
		ySize = 256 * 2 + 80;
		{
			this.buttonList.clear();
			//this.buttonList.add(buttonYes = new GuiButton(1, width / 2, height / 2 + 30, 60, 20, "Yes"));
			//this.buttonList.add(buttonNo = new GuiButton(2, width / 2, height / 2 + 60, 60, 20, "No"));
			//this.buttonList.add(buttonSelectPart = new GuiButton(3, width / 2, height / 2 + 90, 150, 20, "Different part of Kingdom"));
			//this.buttonList.add(buttonPriceList = new GuiButton(4, width / 2, height / 2 + 120, 60, 20, "PriceList"));
			//this.buttonList.add(buttonSelectBuild = new GuiButton(5, width / 2, height / 2, 60, 20, "Building"));
		}
	}
	
	public void actionPerformed(GuiButton guibutton)
	{
		if(guibutton.id == 1)
		{
			BuildBuilding(BuildingNumberSelected);
		}
		if(guibutton.id == 2)
		{
			this.buttonNo.enabled = false;
			this.buttonYes.enabled = false;
		}
		if(guibutton.id == 3)
		{
			//TODO: making it so that the gui changes
		}
		if(guibutton.id == 4)
		{
			setGuiScreen(1);
		}
		if(guibutton.id == 5)
		{
			setGuiScreen(2);
		}
	}
		
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        
        int xSize = 176;
        int ySize = 166;
        
        ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int Width = scaledresolution.getScaledWidth() + 8;
        int Height = scaledresolution.getScaledHeight(); //TODO
        
        {
        	mc.renderEngine.bindTexture("/mods/TaleOfKingdoms/textures/guis/GuiKingdom.png");
        	int l = (width - 320) / 2;
        	int k = (height - 325) / 2;
        	drawTexturedModalRect(l, k, 0, 0, 256, 256);
        }
        {
        	mc.renderEngine.bindTexture("/mods/TaleOfKingdoms/textures/guis/GuiKingdomBackGround.png");
        	int l = (width - 320) / 2;
        	int k = (height - 325) / 2;
        	drawTexturedModalRect(l + 256, k, 0, 0, 256, 256);
        }
        mc.renderEngine.resetBoundTexture();
        {
        	mc.renderEngine.bindTexture("/mods/TaleOfKingdoms/textures/guis/GuiKingdomInventory.png");
        	int l = (width - 320) / 2;
        	int k = (height - 325) / 2;
        	drawTexturedModalRect(l + 62, k + 256, 0, 0, 203, 69);
        }
    }
    
    public void drawGuiContainerForegroundLayer(int i, int j)
    {
        fontRenderer.drawString((new StringBuilder()).append("Coins: ").append(GoldKeeper.getGoldTotal(player)).toString(), (width - 320) / 2 - 59, (height - 325) / 2 - 71, Color.RED.getRGB());
    }
    
    @Override
    public void onGuiClosed() {}
    
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.keyCode)
        {
            mc.thePlayer.closeScreen();
        }
    }
    
    public static void BuildBuilding(int i)
    {
    	WorldGenBuildingsPart1 worldgen = new WorldGenBuildingsPart1(world, chunkCoords);
    	if(i == 1)
    		worldgen.createBuilding(1);
    }
    
    public static void setGuiScreen(int i)
    {
    	if(i == 1)
    		FMLCommonHandler.instance().showGuiScreen(new GuiPriceList(world, chunkCoords));
    }
}