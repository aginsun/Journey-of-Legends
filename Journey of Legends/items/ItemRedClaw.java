package aginsun.journey.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import aginsun.journey.entities.EntityShuricken;

public class ItemRedClaw extends ItemClaw
{
	public ItemRedClaw(int par1)
	{
		super(par1);
		this.maxStackSize = 1;
		this.setMaxDamage(-1);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par3EntityPlayer.capabilities.isCreativeMode)
        {
            --par1ItemStack.stackSize;
        }

        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

        if (!par2World.isRemote)
        {
            par2World.spawnEntityInWorld(new EntityShuricken(par2World, par3EntityPlayer));
        }

        return par1ItemStack;
    }
}
