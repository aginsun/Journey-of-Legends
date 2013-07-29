package aginsun.journey.events;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import aginsun.journey.JourneyofLegends;

public class JourneySoundLoadEvent 
{
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
        try 
        {
            //event.manager.soundPoolSounds.addSound("journey/startvoice.ogg", JourneyofLegends.class.getResource("/mods/sounds/Startvoice.ogg"));            
            System.out.println("Registered Sounds");
        } 
        catch (Exception e)
        {
            System.err.println("Failed to register one or more sounds.");
        }
    }
}
