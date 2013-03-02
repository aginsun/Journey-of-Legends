package aginsun.taleofkingdoms.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import aginsun.taleofkingdoms.client.guis.GuiShopList;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.GoldValues;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.src.*;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;

public class EntityWeaponKeeper extends EntityCreature
{
    private World worldObj;
    private Integer itemget[];
    private GoldKeeper gold;
    private GoldValues gold1;
    private StringTranslate st;

    public EntityWeaponKeeper(World world)
    {
        super(world);
        itemget = new Integer[200];
        st = StringTranslate.getInstance();
        worldObj = world;
        texture = "/aginsun/textures/smith.png";
        moveSpeed = 1.2F;
        isImmuneToFire = false;
        health = 100;
    }

    protected boolean canDespawn()
    {
        return false;
    }

    public int getMaxHealth()
    {
        return 100;
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

    protected void jump()
    {
    }

    protected boolean isMovementCeased()
    {
        return true;
    }

    public boolean canBePushed()
    {
        return false;
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        if (canInteractWith(entityplayer))
        {
            heal(100);
            int i = 0;
            int j = 0;
            boolean flag = false;
            boolean flag1 = true;
            String s = "";
            for (int k = 256; k < 32000; k++)
            {
                boolean flag2 = false;
                boolean flag3 = true;
                if (Item.itemsList[k] == null)
                {
                    continue;
                }
                ItemStack itemstack = new ItemStack(Item.itemsList[k].itemID, 1, 0);
                Item item = itemstack.getItem();
                if (item instanceof ItemFood)
                {
                    flag2 = true;
                }
                if (item instanceof ItemArmor)
                {
                    flag3 = false;
                }
                if (item instanceof ItemSword)
                {
                    flag3 = false;
                }
                if (item instanceof ItemTool)
                {
                    flag3 = false;
                }
                if (item != null)
                {
                    s = item.getItemName();
                }
                if (s != null)
                {
                    j = gold1.PriceItem(s);
                }
                String s1 = (new StringBuilder(String.valueOf(item.getItemName()))).append(".name").toString();
                String s2 = st.translateKey(s1);
                int l = itemstack.itemID;
                if (l == 261 || l == 262)
                {
                    flag3 = false;
                }
                if (l == 26 || l == 34 || l == 36 || l == 43 || l == 51 || l == 52 || l == 55 || l == 59 || l == 60 || l == 62 || l == 63 || l == 64 || l == 68 || l == 71 || l == 74 || l == 75 || l == 78 || l == 90 || l == 93 || l == 94 || l == 97 || l == 99 || l == 100 || l == 104 || l == 105 || l == 110 || l == 92 || l == 354 || l == Item.flint.itemID || l == Item.clay.itemID || l == Item.ingotIron.itemID || l == Item.diamond.itemID || l == Item.fishRaw.itemID || l == Item.appleRed.itemID || l == Item.silk.itemID || l == Item.feather.itemID || flag2 || flag3)
                {
                    j = 0;
                }
                if (j > 0 && !s1.equals("null.name") && !s1.equals(s2))
                {
                    itemget[i] = Integer.valueOf(itemstack.itemID);
                    i++;
                }
            }
            if(FMLCommonHandler.instance().getEffectiveSide().isClient())
            	FMLCommonHandler.instance().showGuiScreen(new GuiShopList(entityplayer, worldObj, itemget));
        }
        return true;
    }
}
