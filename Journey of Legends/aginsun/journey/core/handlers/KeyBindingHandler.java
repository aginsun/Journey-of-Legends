package aginsun.journey.core.handlers;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import aginsun.journey.client.guis.GuiStats;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.TickType;

public class KeyBindingHandler extends KeyHandler
{
	public static KeyBinding StatsScreen = new KeyBinding("Tale of Kingdoms 2 StatScreen", Keyboard.KEY_O);

	public static KeyBinding[] arrayOfKeys = new KeyBinding[] {StatsScreen};
	public static boolean[] areRepeating = new boolean[] {false};

	public KeyBindingHandler() 
	{
	  super(arrayOfKeys, areRepeating);
	}
	
	@Override
	public String getLabel() 
	{
	  return "Tale of Kingdoms 2 Keybindings";
	}
	
	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) 
	{
		if (FMLClientHandler.instance().getClient().currentScreen == null) 
		{
			if(kb.keyCode == StatsScreen.keyCode) 
			{
				FMLCommonHandler.instance().showGuiScreen(new GuiStats());
			}
		}
	}
	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) 
	{
	 
	}
	@Override
	public EnumSet<TickType> ticks() 
	{
		return EnumSet.of(TickType.CLIENT);
	}
}
