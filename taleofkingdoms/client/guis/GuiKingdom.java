package aginsun.taleofkingdoms.client.guis;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import org.lwjgl.opengl.GL11;

import aginsun.taleofkingdoms.entities.TileEntityKingdom;
import aginsun.taleofkingdoms.inventory.ContainerKingdom;

public class GuiKingdom extends GuiContainer
{
	public GuiKingdom(InventoryPlayer inventory, TileEntityKingdom tileEntity)
	{
		super(new ContainerKingdom(tileEntity, inventory));
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		xSize = 256 * 3;
		ySize = 256 * 2 + 80;
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
        	mc.renderEngine.bindTexture("/mods/TaleOfKingdoms/textures/guis/GuiKingdomBackGround.png");
        	int l = (width - xSize) / 2;
        	int k = (height - ySize) / 2;
        	drawTexturedModalRect(l, k, 0, 0, xSize, ySize);
        	drawTexturedModalRect(l + xSize, k, 0, 0, xSize, ySize);
        	drawTexturedModalRect(l - xSize, k, 0, 0, xSize, ySize);
        	drawTexturedModalRect(l + xSize, k - ySize, 0, 0, xSize, ySize);
        	drawTexturedModalRect(l, k - ySize, 0, 0, xSize, ySize);
        	drawTexturedModalRect(l - xSize, k - ySize, 0, 0, xSize, ySize);

        }
        mc.renderEngine.resetBoundTexture();
        {
        	mc.renderEngine.bindTexture("/mods/TaleOfKingdoms/textures/guis/GuiKingdomInventory.png");
        	int l = (width - xSize) / 2;
        	int k = (height - ySize) / 2;
        	drawTexturedModalRect(l, k + ySize, 0, 0, xSize, ySize);
        }
        mc.renderEngine.resetBoundTexture();
        {
        	mc.renderEngine.bindTexture("/mods/TaleOfKingdoms/textures/guis/GuiKingdomSlot.png");
        	int l = (width - xSize) / 2;
        	int k = (height - ySize) / 2;
        	drawTexturedModalRect(l + (xSize * 2) - 52, k + ySize - 52, 0, 0, xSize, ySize);
        	drawTexturedModalRect(l + (xSize * 2) - 52, k + ySize - 88, 0, 0, xSize, ySize);
        	drawTexturedModalRect(l + (xSize * 2) - 52, k + ySize - 124, 0, 0, xSize, ySize);
        }
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
}
