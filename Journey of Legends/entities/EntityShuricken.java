package aginsun.journey.entities;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShuricken extends EntityThrowable 
{
    public EntityShuricken(World par1World)
    {
        super(par1World);
    }

    public EntityShuricken(World par1World, EntityLiving par2EntityLiving)
    {
        super(par1World, par2EntityLiving);
    }

    public EntityShuricken(World par1World, double par2, double par4, double par6)
    {
        super(par1World, par2, par4, par6);
    }
	
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        if (par1MovingObjectPosition.entityHit != null)
        {
            par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 2);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
