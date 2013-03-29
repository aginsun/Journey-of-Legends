package aginsun.taleofkingdoms.client.core;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;


public class ClientTickHandler implements ITickHandler
{
	public Minecraft mc;
    public World world;
    public GoldKeeper gold;
	
    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData)
    {
        if (type.equals(EnumSet.of(TickType.RENDER)))
        {
            onRenderTick();
        }
        else if (type.equals(EnumSet.of(TickType.CLIENT)))
        {
            GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
            if(guiscreen != null)
            {
                onTickInGUI(guiscreen);
            }else{
                onTickInGame();

            }            
        }
    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.RENDER, TickType.CLIENT);
    }

    @Override
    public String getLabel() { return null; }


    public void onRenderTick()
    {

    }

    public void onTickInGUI(GuiScreen guiscreen)
    {
            
	}
    


    public void onTickInGame()
    {
    	
    }
}
