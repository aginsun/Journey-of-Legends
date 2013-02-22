package aginsun.taleofkingdoms.core.helpers;

import aginsun.taleofkingdoms.items.InitItems;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ItemDropHelper 
{
	public static World world = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
    public static boolean isHostileEntity(EntityLiving entity) 
    {
        if ((entity instanceof EntityBlaze) || 
            (entity instanceof EntityCaveSpider) ||
            (entity instanceof EntityCreeper) ||
            (entity instanceof EntityDragon) ||
            (entity instanceof EntityEnderman) ||
            (entity instanceof EntityGhast) ||
            (entity instanceof EntityMagmaCube) ||
            (entity instanceof EntityPigZombie) ||
            (entity instanceof EntitySilverfish) ||
            (entity instanceof EntitySkeleton) ||
            (entity instanceof EntitySpider) ||
            (entity instanceof EntityWitch) ||
            (entity instanceof EntityWither) || 
            (entity instanceof EntityZombie)) 
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
        
	public static void dropCoins(EntityPlayer player, EntityLiving entity) 
	{
			if (isHostileEntity(entity) && !world.isRemote) 
			{
				for(int i = 0; i < 15; i++)
				{
					entity.dropItem(InitItems.ItemCoins.itemID, 1);
				}
			}
		}
}

