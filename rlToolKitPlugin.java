package net.runelite.client.plugins.rlToolKit;


//Mandatory imports
import com.google.inject.Provides;
import javax.inject.Inject;

import net.runelite.api.coords.LocalPoint;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Actor;
import net.runelite.api.Player;
import net.runelite.api.AnimationID;
import net.runelite.;

//Plugin description
@PluginDescriptor(
        name = "rlToolKit",
        description = "Set of customized tools for the boys",
        tags = {"rl", "Tool", "Kit", "rlToolKit", "CRPL:"},
        loadWhenOutdated = true
)
public class rlToolKitPlugin extends Plugin{
    //This injects our rlToolKit.java so we can retrieve/update data in our config window
    @Inject
    private rlToolKitConfig config;
    //we can access our config class through the use of the 'config' keyword
    //Config window does not appear without code below
    @Provides
    rlToolKitConfig provideConfig(ConfigManager configManager){
        return configManager.getConfig(rlToolKitConfig.class);
    }
    //^^^^^^^





    //Variables
    poinTime[] array = new poinTime[100];




    //Context Menu options
    private static final String TAG = "Tag";
    private static final String UNTAG = "Un-tag";
    private static final String UNTAG_ALL = "Un-tag-All";


    //Method to test if animationID is interrupted by performing actions
    public int printAnimation(Player target){
        return target.getAnimation();
    }

    //gets the local point of our target
    public LocalPoint logPoint(Player target){
        return target.getLocalPoint();
    }



}
