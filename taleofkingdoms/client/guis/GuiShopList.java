package aginsun.taleofkingdoms.client.guis;

import org.lwjgl.opengl.GL11;

import aginsun.taleofkingdoms.TaleOfKingdoms;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.GoldValues;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.Player;

public class GuiShopList extends GuiScreen
{
    public World worldObj = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(0);
    public EntityPlayer entityplayer;
    public GoldKeeper gold;
    public StringTranslate st;
    int checkBounty;
    int currentGui;
    private Integer item[];
    private Integer itemget[];
    boolean reachedend;
    public Integer itemSelected;
    boolean goldchecker;
    String stringGet;
    String stringoutput;
    int price;
    public int shopcounter;
    public static TaleOfKingdoms taleofkingdoms;

    public GuiShopList(EntityPlayer entityplayer1, World world, Integer ainteger[])
    {
        st = StringTranslate.getInstance();
        checkBounty = 0;
        currentGui = 0;
        item = new Integer[200];
        itemget = new Integer[200];
        reachedend = false;
        goldchecker = false;
        stringGet = "";
        stringoutput = "";
        shopcounter = 20;
        itemget = ainteger;
        entityplayer = entityplayer1;
        worldObj = world;
        setItemList();
        itemSelected = itemget[0];
    }

    public void setItemList()
    {
        int i = 0;
        i += currentGui * 16;
        for (int j = 0; j <= 16; j++)
        {
            if (itemget[i + j] != null)
            {
                item[j + 1] = itemget[i + j];
            }
            else
            {
                item[j + 1] = null;
            }
        }

        if (itemget[i + 17] == null)
        {
            reachedend = true;
        }
        else
        {
            reachedend = false;
        }
        initGui();
    }

    public void initGui()
    {
        controlList.clear();
        if (item[1] != null)
        {
            Item item1 = (new ItemStack(item[1].intValue(), 1, 0)).getItem();
            String s = (new StringBuilder(String.valueOf(item1.getItemName()))).append(".name").toString();
            s = st.translateKey(s);
            controlList.add(new GuiButtonShop(item[1], this, 0, width / 2 - 90, 40, 90, 20, s));
        }
        if (item[2] != null)
        {
            Item item2 = (new ItemStack(item[2].intValue(), 1, 0)).getItem();
            String s1 = (new StringBuilder(String.valueOf(item2.getItemName()))).append(".name").toString();
            s1 = st.translateKey(s1);
            controlList.add(new GuiButtonShop(item[2], this, 1, width / 2 - 90, 60, 90, 20, s1));
        }
        if (item[3] != null)
        {
            Item item3 = (new ItemStack(item[3].intValue(), 1, 0)).getItem();
            String s2 = (new StringBuilder(String.valueOf(item3.getItemName()))).append(".name").toString();
            s2 = st.translateKey(s2);
            controlList.add(new GuiButtonShop(item[3], this, 2, width / 2 - 90, 80, 90, 20, s2));
        }
        if (item[4] != null)
        {
            Item item4 = (new ItemStack(item[4].intValue(), 1, 0)).getItem();
            String s3 = (new StringBuilder(String.valueOf(item4.getItemName()))).append(".name").toString();
            s3 = st.translateKey(s3);
            controlList.add(new GuiButtonShop(item[4], this, 3, width / 2 - 90, 100, 90, 20, s3));
        }
        if (item[5] != null)
        {
            Item item5 = (new ItemStack(item[5].intValue(), 1, 0)).getItem();
            String s4 = (new StringBuilder(String.valueOf(item5.getItemName()))).append(".name").toString();
            s4 = st.translateKey(s4);
            controlList.add(new GuiButtonShop(item[5], this, 4, width / 2 - 90, 120, 90, 20, s4));
        }
        if (item[6] != null)
        {
            Item item6 = (new ItemStack(item[6].intValue(), 1, 0)).getItem();
            String s5 = (new StringBuilder(String.valueOf(item6.getItemName()))).append(".name").toString();
            s5 = st.translateKey(s5);
            controlList.add(new GuiButtonShop(item[6], this, 5, width / 2 - 90, 140, 90, 20, s5));
        }
        if (item[7] != null)
        {
            Item item7 = (new ItemStack(item[7].intValue(), 1, 0)).getItem();
            String s6 = (new StringBuilder(String.valueOf(item7.getItemName()))).append(".name").toString();
            s6 = st.translateKey(s6);
            controlList.add(new GuiButtonShop(item[7], this, 6, width / 2 - 90, 160, 90, 20, s6));
        }
        if (item[8] != null)
        {
            Item item8 = (new ItemStack(item[8].intValue(), 1, 0)).getItem();
            String s7 = (new StringBuilder(String.valueOf(item8.getItemName()))).append(".name").toString();
            s7 = st.translateKey(s7);
            controlList.add(new GuiButtonShop(item[8], this, 7, width / 2 - 90, 180, 90, 20, s7));
        }
        if (item[9] != null)
        {
            Item item9 = (new ItemStack(item[9].intValue(), 1, 0)).getItem();
            String s8 = (new StringBuilder(String.valueOf(item9.getItemName()))).append(".name").toString();
            s8 = st.translateKey(s8);
            controlList.add(new GuiButtonShop(item[9], this, 8, width / 2 + 20, 40, 90, 20, s8));
        }
        if (item[10] != null)
        {
            Item item10 = (new ItemStack(item[10].intValue(), 1, 0)).getItem();
            String s9 = (new StringBuilder(String.valueOf(item10.getItemName()))).append(".name").toString();
            s9 = st.translateKey(s9);
            controlList.add(new GuiButtonShop(item[10], this, 9, width / 2 + 20, 60, 90, 20, s9));
        }
        if (item[11] != null)
        {
            Item item11 = (new ItemStack(item[11].intValue(), 1, 0)).getItem();
            String s10 = (new StringBuilder(String.valueOf(item11.getItemName()))).append(".name").toString();
            s10 = st.translateKey(s10);
            controlList.add(new GuiButtonShop(item[11], this, 10, width / 2 + 20, 80, 90, 20, s10));
        }
        if (item[12] != null)
        {
            Item item12 = (new ItemStack(item[12].intValue(), 1, 0)).getItem();
            String s11 = (new StringBuilder(String.valueOf(item12.getItemName()))).append(".name").toString();
            s11 = st.translateKey(s11);
            controlList.add(new GuiButtonShop(item[12], this, 11, width / 2 + 20, 100, 90, 20, s11));
        }
        if (item[13] != null)
        {
            Item item13 = (new ItemStack(item[13].intValue(), 1, 0)).getItem();
            String s12 = (new StringBuilder(String.valueOf(item13.getItemName()))).append(".name").toString();
            s12 = st.translateKey(s12);
            controlList.add(new GuiButtonShop(item[13], this, 12, width / 2 + 20, 120, 90, 20, s12));
        }
        if (item[14] != null)
        {
            Item item14 = (new ItemStack(item[14].intValue(), 1, 0)).getItem();
            String s13 = (new StringBuilder(String.valueOf(item14.getItemName()))).append(".name").toString();
            s13 = st.translateKey(s13);
            controlList.add(new GuiButtonShop(item[14], this, 13, width / 2 + 20, 140, 90, 20, s13));
        }
        if (item[15] != null)
        {
            Item item15 = (new ItemStack(item[15].intValue(), 1, 0)).getItem();
            String s14 = (new StringBuilder(String.valueOf(item15.getItemName()))).append(".name").toString();
            s14 = st.translateKey(s14);
            controlList.add(new GuiButtonShop(item[15], this, 14, width / 2 + 20, 160, 90, 20, s14));
        }
        if (item[16] != null)
        {
            Item item16 = (new ItemStack(item[16].intValue(), 1, 0)).getItem();
            String s15 = (new StringBuilder(String.valueOf(item16.getItemName()))).append(".name").toString();
            s15 = st.translateKey(s15);
            controlList.add(new GuiButtonShop(item[16], this, 15, width / 2 + 20, 180, 90, 20, s15));
        }
        controlList.add(new GuiButton(17, width / 2 - 120, 220, 70, 20, "Back"));
        controlList.add(new GuiButton(16, width / 2 - 120, 200, 70, 20, "Next"));
        controlList.add(new GuiButton(18, width / 2 - 35, 200, 70, 20, "Buy Item"));
        controlList.add(new GuiButton(19, width / 2 + 50, 220, 70, 20, "Exit"));
        //controlList.add(new GuiButton(20, width / 2 + 50, 200, 70, 20, "Sell Item"));
        controlList.add(new GuiButton(21, width / 2 - 35, 220, 70, 20, "Buy 16 Items"));
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    public void onGuiClosed()
    {
    	if(!worldObj.isRemote)
    	{
    		entityplayer.addChatMessage("Shop Keeper: Thank you! Come back again!");
    	}
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if (guibutton.id == 0)
        {
            itemSelected = item[1];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item1 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item1.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item1.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item1 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 1)
        {
            itemSelected = item[2];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item2 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item2.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item2.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item2 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 2)
        {
            itemSelected = item[3];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item3 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item3.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item3.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item3 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 3)
        {
            itemSelected = item[4];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item4 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item4.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item4.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item4 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 4)
        {
            itemSelected = item[5];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item5 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item5.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item5.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item5 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 5)
        {
            itemSelected = item[6];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item6 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item6.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item6.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item6 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 6)
        {
            itemSelected = item[7];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item7 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item7.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item7.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item7 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 7)
        {
            itemSelected = item[8];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item8 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item8.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item8.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item8 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 8)
        {
            itemSelected = item[9];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item9 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item9.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item9.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item9 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 9)
        {
            itemSelected = item[10];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item10 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item10.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item10.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item10 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 10)
        {
            itemSelected = item[11];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item11 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item11.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item11.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item11 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 11)
        {
            itemSelected = item[12];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item12 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item12.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item12.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item12 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 12)
        {
            itemSelected = item[13];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item13 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item13.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item13.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item13 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 13)
        {
            itemSelected = item[14];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item14 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item14.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item14.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item14 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 14)
        {
            itemSelected = item[15];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item15 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item15.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item15.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item15 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 15)
        {
            itemSelected = item[16];
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item16 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item16.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item16.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item16 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 16)
        {
            if (!reachedend)
            {
                currentGui++;
            }
            setItemList();
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item17 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item17.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item17.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item17 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 17)
        {
            if (currentGui != 0)
            {
                currentGui = currentGui - 1;
            }
            setItemList();
            goldchecker = false;
            if (itemSelected != null)
            {
                Item item18 = (new ItemStack(itemSelected.intValue(), 1, 0)).getItem();
                stringGet = (new StringBuilder(String.valueOf(item18.getItemName()))).append(".name").toString();
                if (stringGet != null)
                {
                    stringoutput = st.translateKey(stringGet);
                }
                price = GoldValues.PriceItem(String.valueOf(item18.getItemName()));
                price += (double)price * 0.80000000000000004D;
                if (item18 instanceof ItemFood)
                {
                    price += (double)price * 0.20000000000000001D;
                }
            }
        }
        if (guibutton.id == 18)
        {
            ItemStack itemstack = new ItemStack(itemSelected.intValue(), 1, 0);
            Item item19 = itemstack.getItem();	
            String s = item19.getItemName();
            int i = GoldValues.PriceItem(s);
            i = (int)((double)i + (double)i * 0.80000000000000004D);
            if (item19 instanceof ItemFood)
            {
                i = (int)((double)i + (double)i * 0.20000000000000001D);
            }
            if (i <= gold.getGoldTotal(entityplayer))
            {
                EntityItem entityitem = new EntityItem(worldObj, entityplayer.posX, entityplayer.posY, entityplayer.posZ, itemstack);
                worldObj.spawnEntityInWorld(entityitem);
                gold.removeGold(entityplayer, i);
            }
            else
            {
                goldchecker = true;
            }
        }
        if (guibutton.id == 21 && shopcounter >= 16)
        {
            ItemStack itemstack1 = new ItemStack(itemSelected.intValue(), 1, 0);
            Item item20 = itemstack1.getItem();
            String s1 = item20.getItemName();
            int j = GoldValues.PriceItem(s1);
            j = (int)((double)j + (double)j * 0.80000000000000004D);
            if (item20 instanceof ItemFood)
            {
                j = (int)((double)j + (double)j * 0.20000000000000001D);
            }
            if (j * 16 <= gold.getGoldTotal(entityplayer))
            {
                shopcounter = 0;
            }
            else
            {
                goldchecker = true;
            }
        }
        if (guibutton.id == 19)
        {
            mc.displayGuiScreen(null);
            goldchecker = false;
        }
        /*if (guibutton.id == 20)
        {
        	
        	EntityPlayer thePlayer = (EntityPlayer) player;
            entityplayer.openGui(taleofkingdoms.instance, 1, worldObj, -54, 65, 33);
            goldchecker = false;
        }*/
    }

    public void drawScreen(int i, int j, float f)
    {
        if (shopcounter < 16)
        {
            ItemStack itemstack = new ItemStack(itemSelected.intValue(), 1, 0);
            Item item1 = itemstack.getItem();
            String s = item1.getItemName();
            int i1 = GoldValues.PriceItem(s);
            i1 = (int)((double)i1 + (double)i1 * 0.80000000000000004D);
            if (item1 instanceof ItemFood)
            {
                i1 = (int)((double)i1 + (double)i1 * 0.20000000000000001D);
            }
            if (i1 <= gold.getGoldTotal(entityplayer))
            {
                EntityItem entityitem = new EntityItem(worldObj, entityplayer.posX, entityplayer.posY, entityplayer.posZ, itemstack);
                entityplayer.joinEntityItemWithWorld(entityitem);
                gold.removeGold(entityplayer, i1);
            }
            shopcounter++;
        }
        drawDefaultBackground();
        int k = mc.renderEngine.getTexture("/aginsun/textures/crafting.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        char c = '\377';
        char c1 = '\377';
        mc.renderEngine.bindTexture(k);
        int j1 = (width - c) / 2;
        drawTexturedModalRect(j1, 0, 0, 0, c, c1);
        for (int l = 0; l < controlList.size(); l++)
        {
            if (controlList.get(l) instanceof GuiButtonShop)
            {
                GuiButtonShop guibuttonshop = (GuiButtonShop)controlList.get(l);
                guibuttonshop.drawButton(mc, i, j);
            }
            if (controlList.get(l) instanceof GuiButton)
            {
                GuiButton guibutton = (GuiButton)controlList.get(l);
                guibutton.drawButton(mc, i, j);
            }
        }

        drawCenteredString(fontRenderer, (new StringBuilder()).append("Shop Menu - Total Money: ").append(gold.getGoldTotal(entityplayer)).append(" Gold Coins").toString(), width / 2, 15, 0xffcc00);
        if (goldchecker)
        {
            drawCenteredString(fontRenderer, (new StringBuilder()).append("Selected Item Cost: ").append(stringoutput).append(" - NOT ENOUGH GOLD").toString(), width / 2, 30, 0xffcc00);
        }
        else
        {
            drawCenteredString(fontRenderer, (new StringBuilder()).append("Selected Item Cost: ").append(stringoutput).append(" - ").append(price).append(" Gold coins").toString(), width / 2, 30, 0xffcc00);
        }
        super.drawScreen(i, j, f);
    }
    
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.keyCode)
        {
            mc.thePlayer.closeScreen();
        }
    }
    
    public Player player;
}

