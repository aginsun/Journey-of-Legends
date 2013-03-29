package aginsun.taleofkingdoms.client.guis;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import aginsun.taleofkingdoms.core.goldSystem.GoldKeeper;
import aginsun.taleofkingdoms.core.goldSystem.HunterKeeper;
import aginsun.taleofkingdoms.core.goldSystem.RaceKeeper;
import aginsun.taleofkingdoms.core.goldSystem.StatKeeper;
import aginsun.taleofkingdoms.core.goldSystem.WorthyKeeper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGuildMaster extends GuiScreen
{
    private World worldObj;
    public EntityPlayer player;
    private GoldKeeper gold;
    private StatKeeper stats;
    boolean goldchecker;
    private GuiPriceBar worthness;
    private float worthyness;
    public HunterKeeper hunter;
    public WorthyKeeper worthy;

    public GuiGuildMaster(EntityPlayer entityplayer1, World world)
    {
        goldchecker = false;
        player = entityplayer1;
        worldObj = world;
    }

    public void initGui()
    {
        String s;
        if (!hunter.getHunterStatus(player))
        {
            s = "Sign up contract!";
        }
        else if(stats.getLevel(player) >= 10)
        {
        	s = "Choose your Class!";
        }
        else if(stats.getLevel(player) >= 30)
        {
        	s = "Claim your Kingdom!";
        }       
        else
        {
            s = "Cancel contract.";
        }
        buttonList.clear();
        buttonList.add(new GuiButton(1, width / 2 + 110, 140, 100, 20, s));
        buttonList.add(new GuiButton(2, width / 2 + 110, 160, 100, 20, "Information"));
        buttonList.add(new GuiButton(4, width / 2 + 110, 180, 100, 20, "Fix the Guild"));
        buttonList.add(new GuiButton(5, width / 2 + 110, 200, 100, 20, "Retire Hunters"));
        buttonList.add(new GuiButton(3, width / 2 + 110, 220, 100, 20, "Exit"));
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if (guibutton.id == 1 && !hunter.getHunterStatus(player))
        {
            if (!worldObj.isRemote)
            {
                player.addChatMessage("Guild Master: You are now one of us my friend. Kill monsters and you will soon be worthy of your title.");
            }
            hunter.setHunterStatus(player, true);
            initGui();
        }
        else if (guibutton.id == 1 && hunter.getHunterStatus(player) && stats.getLevel(player) >= 10 && stats.getLevel(player) < 30 && RaceKeeper.getClass(player).equals("Beginner"))
        {
        	FMLCommonHandler.instance().showGuiScreen(null);
        	FMLCommonHandler.instance().showGuiScreen(new GuiRaceSelect(player));
        }
        
        if(guibutton.id == 2)
        {
        	if(!worldObj.isRemote)
        	{
        		player.addChatMessage("Guild Master: You will need to get to level 30.");
        		player.addChatMessage("Guild Master: If so, you will be able to get your own kingdom! (needs to be added)");
        		player.addChatMessage("Aginsun: More information will be added in the future!");
        	}
        }

        if (guibutton.id == 3)
        {
            mc.displayGuiScreen(null);
            goldchecker = false;
        }
        if (guibutton.id == 4)
        {
        	
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
    		player.addChatMessage("Guild Master: Good Hunting.");
    	}
    }

    public void drawScreen(int i, int j, float f)
    {
        for (int k = 0; k < buttonList.size(); k++)
        {
            GuiButton guibutton = (GuiButton)buttonList.get(k);
            guibutton.drawButton(mc, i, j);
        }

        if (goldchecker)
        {
            drawCenteredString(fontRenderer, (new StringBuilder()).append("The Guild Order  Total Money: ").append(gold.getGoldTotal(player)).append(" Gold Coins - NOT ENOUGH GOLD").toString(), width / 2, 20, 0xffee00);
        }
        else
        {
            drawCenteredString(fontRenderer, (new StringBuilder()).append("The Guild Order  Total Money: ").append(gold.getGoldTotal(player)).append(" Gold Coins").toString(), width / 2, 0, 0xffee00);
        }
        drawCenteredString(fontRenderer, "Note: Hiring Cost 1500 gold, Retiring will Refund 1000. Fixing the Guild need 64 wood.", width / 2, 10, 0xffee00);
    }
    
    protected void keyTyped(char par1, int par2)
    {
        if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.keyCode)
        {
            mc.thePlayer.closeScreen();
        }
    }
}
