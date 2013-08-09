package aginsun.journey.client.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import aginsun.journey.server.api.GoldKeeper;
import aginsun.journey.universal.entities.TileEntitySell;
import aginsun.journey.universal.inventory.ContainerSell;
import cpw.mods.fml.client.FMLClientHandler;

public class GuiSell extends GuiContainer
{
    private GoldKeeper gold;
    private EntityPlayer entityplayer = FMLClientHandler.instance().getClient().thePlayer;
    private World world;
   

    public GuiSell(InventoryPlayer player_inventory, TileEntitySell tileentitysell)
    {
        super(new ContainerSell(tileentitysell, player_inventory));
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        fontRenderer.drawString("Total Money: ", 25, 40, 0x404040);
        fontRenderer.drawString((new StringBuilder()).append(gold.getGoldTotal(entityplayer)).append(" Gold Coins").toString(), 30, 50, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
        fontRenderer.drawString("Sell Menu", 25, 20, 0x404040);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation resource = new ResourceLocation("journeyoflegends", "textures/guis/guisell.png");
        mc.renderEngine.func_110577_a(resource);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
    }
    
    @Override
    public void onGuiClosed()
    {

    }
    
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.keyCode)
        {
            mc.thePlayer.closeScreen();
        }
    }
}
