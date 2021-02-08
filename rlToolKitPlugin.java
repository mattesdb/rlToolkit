package net.runelite.client.plugins.rlToolKit;


//Mandatory imports
import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import javax.inject.Provider;
import javax.inject.Inject;
import javax.annotation.Nullable;

//Runelite API
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Actor;
import net.runelite.api.Player;
import net.runelite.api.AnimationID;
import net.runelite.client.plugins.rlToolKit.pointTime;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.eventbus.Subscribe;

//Java Libraries
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

    //MenuManager
    @Inject
    private Provider<MenuManager> menuManager;

    @Inject
    @Nullable
    private Client client;


    //Tracked tile queue, this represents FIFO list of tiles our target has been on
    Queue<pointTime> ptQ = new LinkedList<>();
    //pointer to our designated actor
    public Actor trackedPlayer = null;

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
        return target.getLocalLocation();
    }
    //Assign our designated actor
    public int assignTarget(Actor a){
        this.trackedPlayer = a;
        return 1;
    }

    //Custom Menu Entry
    private static final String ACQUIRE_TARGET = "Acquire Target";
    //StartUp Plugin Override
    @Override
    protected void startUp() throws Exception{
        menuManager.get().addPlayerMenuItem(ACQUIRE_TARGET);
    }

    //Logic for custom menu entry onClick()
    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event){
        if (event.getMenuAction() == MenuAction.RUNELITE_PLAYER && event.getMenuOption().equals(ACQUIRE_TARGET)){
            Player p = client.getCachedPlayers()[event.getId()];

        }
    }




}
