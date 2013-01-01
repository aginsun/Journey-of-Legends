package aginsun.taleofkingdoms.client.guis;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class GuiPriceBar extends Gui
{
    public int xPosition;
    public int borderColor;
    public int yPosition;
    private int width;
    private int height;
    public float barPosition;
    public int id;
    public boolean border;
    public int colour;

    public GuiPriceBar(int i, int j, int k, int l, int i1, float f)
    {
        borderColor = 0xff5e5e5e;
        if (f >= 0.0F && f <= 1.0F)
        {
            barPosition = f;
        }
        else
        {
            barPosition = 0.0F;
        }
        id = i;
        colour = 0xffd90b0b;
        xPosition = j;
        yPosition = k;
        width = l;
        height = i1;
        border = true;
    }
    
    public GuiPriceBar(int i, int j, int k, int l, int i1, float f, int j1)
    {
        this(i, j, k, l, i1, f);
        colour = j1;
    }

    public GuiPriceBar(int i, int j, int k, int l, int i1, float f, String s)
    {
        this(i, j, k, l, i1, f);
        if (s.equalsIgnoreCase("red"))
        {
            colour = 0xffd90b0b;
        }
        else if (s.equalsIgnoreCase("green"))
        {
            colour = 0xff074f11;
        }
        else if (s.equalsIgnoreCase("blue"))
        {
            colour = 0xff1b1be0;
        }
        else
        {
            colour = -1;
        }
    }
    public void setBar(float f)
    {
        if (f >= 0.0F && f <= 1.0F)
        {
            barPosition = f;
        }
    }

    public void drawBar()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (border)
        {
            drawRect(xPosition, yPosition, xPosition + width, yPosition + height, borderColor);
        }
        drawRect(xPosition + 1, yPosition + 1, (xPosition + 1 + width) - 2, (yPosition + 1 + height) - 2, 0xff000000);
        drawRect(xPosition + 1, yPosition + 1, xPosition + 1 + (int)(barPosition * (float)(width - 2)), (yPosition + 1 + height) - 2, colour);
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j)
    {
        return i >= xPosition && j >= yPosition && i < xPosition + width && j < yPosition + height;
    }
}
