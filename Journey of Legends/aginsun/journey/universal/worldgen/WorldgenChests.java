package aginsun.journey.universal.worldgen;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import aginsun.journey.universal.items.InitItems;

public class WorldgenChests
{
	public static void Init()
	{
		WeightedRandomChestContent x = new WeightedRandomChestContent(new ItemStack(InitItems.ItemExcalibur), 50, 95, 4);
		ChestGenHooks.getInfo(ChestGenHooks.MINESHAFT_CORRIDOR).addItem(x);
	}
}
