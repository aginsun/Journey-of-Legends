package aginsun.journey.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;


public class GuiButtonShop extends GuiButton
{
    private GuiShopList gui;
    private GuiRaceSelect gui1;
    private Item item;
    private Integer itemID;
    private boolean isSword;
    private int selected;
    private Integer ClassSelected;
    private String name;
    protected int width;
    private String race;
    protected int height;
    public int xPosition;
    public int yPosition;
    public String displayString;
    public int id;
    public boolean enabled;
    public boolean enabled2;

    public GuiButtonShop(Integer integer, GuiShopList guishoplist, int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, 200, 20, s);
        isSword = false;
        selected = 0;
        gui = guishoplist;
        itemID = integer;
        Item item1 = (new ItemStack(integer.intValue(), 1, 0)).getItem();
        item = item1;
        width = 200;
        height = 20;
        enabled = true;
        enabled2 = true;
        id = i;
        xPosition = j;
        yPosition = k;
        width = l;
        height = i1;
        displayString = s;
    }

    public GuiButtonShop(GuiRaceSelect gui, int i, int j, int k, int l, int i1, String s)
    {
        super(i, j, k, 200, 20, s);
        isSword = false;
        selected = 0;
        gui1 = gui;
        width = 200;
        height = 20;
        enabled = true;
        enabled2 = true;
        id = i;
        xPosition = j;
        yPosition = k;
        width = l;
        height = i1;
        displayString = s;
    }
    /*
    public GuiButtonShop(int i, GuiTOK guitok, int j, int k, int l, int i1, int j1, String s, boolean flag)
    {
        super(j, k, l, 200, 20, s);
        isSword = false;
        selected = 0;
        gui3 = guitok;
        width = 200;
        height = 20;
        enabled = true;
        enabled2 = true;
        id = j;
        xPosition = k;
        yPosition = l;
        width = i1;
        height = j1;
        displayString = s;
        isSword = flag;
        selected = i;
    }

    public GuiButtonShop(String s, GuiTOK guitok, int i, int j, int k, int l, int i1, String s1, boolean flag)
    {
        super(i, j, k, 200, 20, s1);
        isSword = false;
        selected = 0;
        gui4 = guitok;
        width = 200;
        height = 20;
        enabled = true;
        enabled2 = true;
        id = i;
        xPosition = j;
        yPosition = k;
        width = l;
        height = i1;
        displayString = s1;
        isSword = flag;
        name = s;
    }*/

    protected int getHoverState(boolean flag)
    {
        byte byte0 = 1;
        if (!enabled)
        {
            byte0 = 0;
        }
        else if (flag)
        {
            byte0 = 2;
        }
        return byte0;
    }

    public void drawButton(Minecraft minecraft, int i, int j)
    {
        if (!enabled2)
        {
            return;
        }
        FontRenderer fontrenderer = minecraft.fontRenderer;
        minecraft.renderEngine.bindTexture("/aginsun/textures/gui.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        boolean flag = i >= xPosition && j >= yPosition && i < xPosition + width && j < yPosition + height;
        int k = getHoverState(flag);
        if (gui != null)
        {
            if (gui.itemSelected == itemID)
            {
                k = 2;
            }
            else
            {
                k = 1;
            }
        }
        
        if(gui1 != null)
        {
        	if(gui1.buttonSelected == id)
        	{
        		k = 2;
        	}
        	else
        	{
        		k = 1;
        	}
        }
        
        /*
        if (gui3 != null)
        {
            if (selected == TaleOfKingdoms.Excalibur.shiftedIndex)
            {
                k = 2;
            }
            else
            {
                k = 1;
            }
        }
        if (gui4 != null)
        {
            ItemExcalibur itemexcalibur = (ItemExcalibur)TaleOfKingdoms.Excalibur;
            if (name.equals(itemexcalibur.aura))
            {
                k = 2;
            }
            else
            {
                k = 1;
            }
        }*/
        drawTexturedModalRect(xPosition, yPosition, 0, 46 + k * 20, width / 2, height);
        drawTexturedModalRect(xPosition + width / 2, yPosition, 200 - width / 2, 46 + k * 20, width / 2, height);
        mouseDragged(minecraft, i, j);
        if (!enabled)
        {
            drawCenteredString(fontrenderer, displayString, (xPosition + width / 2) - 20, yPosition + (height - 8) / 2, 0xffffcc00);
        }
        else if (!flag)
        {
            drawCenteredString(fontrenderer, displayString, (xPosition + width / 2) - 20, yPosition + (height - 8) / 2, 0xffffff);
        }
        else
        {
            drawCenteredString(fontrenderer, displayString, (xPosition + width / 2) - 20, yPosition + (height - 8) / 2, 0x00cc00);
        }
    }
}
