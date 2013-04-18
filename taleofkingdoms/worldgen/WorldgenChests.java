package aginsun.taleofkingdoms.worldgen;

import java.util.Random;
import aginsun.taleofkingdoms.items.InitItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ChestGenHooks;

public class WorldgenChests
{
	public static void Init()
	{
		Random rand = new Random();
		ChestGenHooks.generateStacks(rand, new ItemStack(InitItems.ItemExcalibur), 1, 1);
	}
}
