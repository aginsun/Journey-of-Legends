package aginsun.taleofkingdoms.client.guis;

import aginsun.taleofkingdoms.TaleOfKingdoms;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.HunterKeeper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiGuildMaster extends GuiScreen
{
    private World worldObj;
    public EntityPlayer entityplayer;
    private GoldKeeper gold;
    boolean goldchecker;
    private GuiPriceBar worthness;
    private float worthyness;
    public HunterKeeper hunter;

    public GuiGuildMaster(EntityPlayer entityplayer1, World world)
    {
        goldchecker = false;
        entityplayer = entityplayer1;
        worldObj = world;
    }

    public void initGui()
    {
        String s;
        if (!hunter.getHunterStatus(entityplayer))
        {
            s = "Sign up contract!";
        }
        else
        {
            s = "Cancel contract.";
        }
        controlList.clear();
        controlList.add(new GuiButton(1, width / 2 + 110, 140, 100, 20, s));
        controlList.add(new GuiButton(2, width / 2 + 110, 160, 100, 20, "Hire Hunters"));
        controlList.add(new GuiButton(4, width / 2 + 110, 180, 100, 20, "Fix the Guild"));
        controlList.add(new GuiButton(5, width / 2 + 110, 200, 100, 20, "Retire Hunters"));
        controlList.add(new GuiButton(3, width / 2 + 110, 220, 100, 20, "Exit"));
        worthness = new GuiPriceBar(0, width / 2 + 110, 120, 125, 12, 1.0F, "red");
        worthness.setBar(worthyness / 7000F);
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if (guibutton.id == 1)
        {
            if (!worldObj.isRemote)
            {
                entityplayer.addChatMessage("Guild Master: You are now one of us my friend. Kill monsters and you will soon be worthy of your title.");
            }
            hunter.setHunterStatus(entityplayer, true);
            initGui();
        }
        else if (guibutton.id == 1)
        {
        	if(!worldObj.isRemote)
        	{
        		entityplayer.addChatMessage("Guild Master: We will await your participation, hero.");
        	}	
        hunter.setHunterStatus(entityplayer, false);
        initGui();
        }       

        if (guibutton.id == 3)
        {
            mc.displayGuiScreen(null);
            goldchecker = false;
        }
        if (guibutton.id == 4)
        {
            InventoryPlayer inventoryplayer = entityplayer.inventory;
            boolean flag = false;
            if (inventoryplayer.hasItem(17))
            {
                for (int j = 0; j < inventoryplayer.mainInventory.length; j++)
                {
                    if (inventoryplayer.mainInventory[j] == null || inventoryplayer.mainInventory[j].itemID != 17)
                    {
                        continue;
                    }
                    ItemStack itemstack = inventoryplayer.getStackInSlot(j);
                    if (itemstack.stackSize == itemstack.getMaxStackSize() && !flag)
                    {
                        inventoryplayer.setInventorySlotContents(j, null);
                        flag = true;
                    }
                }
            }
            if (!flag)
            {
            	if(!worldObj.isRemote)
            	{
            		entityplayer.addChatMessage("Guild Master: We need more resources.");
            	}
            }
            else
            {
            	if(!worldObj.isRemote)
            	{
            		entityplayer.addChatMessage("Guild Master: The guild has been fixed. Thank you.");
            	}
            }
        }
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    public void onGuiClosed()
    {
    	if(!worldObj.isRemote)
    	{
    		entityplayer.addChatMessage("Guild Master: Good Hunting.");
    	}
    }

    public void drawScreen(int i, int j, float f)
    {
        for (int k = 0; k < controlList.size(); k++)
        {
            GuiButton guibutton = (GuiButton)controlList.get(k);
            guibutton.drawButton(mc, i, j);
        }

        drawCenteredString(fontRenderer, "Path to Kingship", width / 2 + 100, 110, 0xaa44ff);
        if (goldchecker)
        {
            drawCenteredString(fontRenderer, (new StringBuilder()).append("The Guild Order  Total Money: ").append(gold.getGoldTotal(entityplayer)).append(" Gold Coins - NOT ENOUGH GOLD").toString(), width / 2, 20, 0xffee00);
        }
        else
        {
            drawCenteredString(fontRenderer, (new StringBuilder()).append("The Guild Order  Total Money: ").append(gold.getGoldTotal(entityplayer)).append(" Gold Coins").toString(), width / 2, 0, 0xffee00);
        }
        drawCenteredString(fontRenderer, "Note: Hiring Cost 1500 gold, Retiring will Refund 1000. Fixing the Guild need 64 wood.", width / 2, 10, 0xffee00);
        worthness.drawBar();
    }
    
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.keyCode)
        {
            mc.thePlayer.closeScreen();
        }
    }
}
