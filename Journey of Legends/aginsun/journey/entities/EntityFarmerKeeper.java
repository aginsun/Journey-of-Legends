package aginsun.journey.entities;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import aginsun.journey.api.GoldKeeper;

public class EntityFarmerKeeper extends EntityCreature
{
    private World worldObj;
    private boolean freebread;
    private ItemStack defaultHeldItem;
    public static GoldKeeper gold;

    public EntityFarmerKeeper(World world)
    {
        super(world);
        freebread = true;
        defaultHeldItem = new ItemStack(Item.hoeIron, 1);
        worldObj = world;
        isImmuneToFire = false;
        setEntityHealth(100.0F);
    }

    public int getMaxHealth()
    {
        return 100;
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        if (isDead)
        {
            return false;
        }
        else
        {
            return entityplayer.getDistanceSqToEntity(this) <= 64D;
        }
    }

    public boolean canBePushed()
    {
        return false;
    }

    protected boolean isMovementCeased()
    {
        return true;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        if (freebread)
        {
        	if(!worldObj.isRemote)
        	{
        		entityplayer.addChatMessage("Farmer: Here, take a bread!");
        	}
            ItemStack itemstack = new ItemStack(297, 1, 0);
            EntityItem entityitem = new EntityItem(worldObj, entityplayer.posX, entityplayer.posY, entityplayer.posZ, itemstack);
            if(!worldObj.isRemote)
            {
            	worldObj.spawnEntityInWorld(entityitem);
            }
            freebread = false;
        }
        else
        {
        	if(!worldObj.isRemote)
        	{
        		entityplayer.addChatMessage("Farmer: You got your bread for now!");
        	}
        }
        return true;
    }

    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }
}
