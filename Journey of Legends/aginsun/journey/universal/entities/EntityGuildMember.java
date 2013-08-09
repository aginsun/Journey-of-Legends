package aginsun.journey.universal.entities;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGuildMember extends EntityCreature
{
    private World worldObj;
    private EntityPlayer player;
    private ItemStack defaultHeldItem;
    private boolean fight;
    private int counter;
    public boolean isSwinging;
    public int swingProgressInt;
    protected int attackStrength;
    private int guildFightEnded = 1;

    public EntityGuildMember(World world)
    {
        super(world);
        defaultHeldItem = new ItemStack(Item.swordIron, 1);
        fight = false;
        counter = 0;
        worldObj = world;
        isImmuneToFire = true;
        setEntityHealth(40.0F);
        attackStrength = 15;
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public int getMaxHealth()
    {
        return 40;
    }

    public boolean canBePushed()
    {
        return false;
    }

    protected boolean isMovementCeased()
    {
        return false;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        player = entityplayer;
        if (guildFightEnded == 1)
        {
        	player.addChatMessage("Greetings " + player.username);
        }
        else if (!worldObj.isRemote)
        {
            entityplayer.addChatMessage("Guild Member: Damn this Reficules");
        }
        return true;
    }

    public void onDeath(DamageSource damagesource)
    {
        if (fight)
        {
        	
            if (player != null && !worldObj.isRemote)
            {
                player.addChatMessage("Guild Member: Your a good fighter my friend, I will let the guild master know of your strength.");
            }
        }
    }

    protected void updateEntityActionState()
    {
        super.updateEntityActionState();
        if (fight)
        {
            counter++;
            if (counter == 10 && !worldObj.isRemote)
            {
                player.addChatMessage("Guild Member: 3");
            }
            if (counter == 20 && !worldObj.isRemote)
            {
                player.addChatMessage("Guild Member: 2");
            }
            if (counter == 30 && !worldObj.isRemote)
            {
                player.addChatMessage("Guild Member: 1");
            }
            if (counter == 40 && !worldObj.isRemote)
            {
                player.addChatMessage("Guild Member: Begin!");
                entityToAttack = player;
            }
        }
        int i = 6;
        if (isSwinging)
        {
            swingProgressInt++;
            if (swingProgressInt >= i)
            {
                swingProgressInt = 0;
                isSwinging = false;
            }
        }
        else
        {
            swingProgressInt = 0;
        }
        swingProgress = (float)swingProgressInt / (float)i;
        if (entityToAttack == null && !hasPath())
        {
            List list = worldObj.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1.0D, posY + 1.0D, posZ + 1.0D).expand(20D, 4D, 20D));
            if (!list.isEmpty())
            {
                Entity entity = (Entity)list.get(worldObj.rand.nextInt(list.size()));
                if (entity instanceof EntityCreeper)
                {
                    entity.setDead();
                }
                else if ((entity instanceof EntityMob))
                {
                	defaultHeldItem = new ItemStack(Item.swordIron, 1);
                    entityToAttack = entity;          
                }
            }
        }
    }

    protected void jump()
    {
        Random random = new Random();
        if (random.nextInt(15) == 0)
        {
            motionY = 0.41999998688697815D;
            if (isSprinting())
            {
                float f = rotationYaw * 0.01745329F;
                motionX -= MathHelper.sin(f) * 0.2F;
                motionZ += MathHelper.cos(f) * 0.2F;
            }
            isAirBorne = true;
        }
    }

    protected void attackEntity(Entity entity, float f)
    {
        if (attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            attackTime = 20;
            swingItem();
            attackEntityAsMob(entity);
        }
    }

    public boolean attackEntityAsMob(Entity entity)
    {
        int i = attackStrength;
        if (isPotionActive(Potion.damageBoost))
        {
            i += 3 << getActivePotionEffect(Potion.damageBoost).getAmplifier();
        }
        if (isPotionActive(Potion.weakness))
        {
            i -= 2 << getActivePotionEffect(Potion.weakness).getAmplifier();
        }
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), i);
    }

    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    public void swingItem()
    {
        if (!isSwinging || swingProgressInt < 0)
        {
            swingProgressInt = -1;
            isSwinging = true;
        }
    }
}
