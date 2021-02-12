package net.runelite.client.plugins.rlToolKit;


//Mandatory imports
import com.google.common.collect.ImmutableSet;
import com.google.inject.Provides;
import javax.inject.Provider;
import javax.inject.Inject;
import javax.annotation.Nullable;
//Runelite API
import net.runelite.api.Client;
import net.runelite.api.RenderOverview;
import net.runelite.api.MenuAction;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.config.ConfigManager;
import net.runelite.api.events.GameTick;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Actor;
import net.runelite.api.Player;
import net.runelite.api.AnimationID;
import net.runelite.client.plugins.rlToolKit.pointTime;
import net.runelite.client.menus.MenuManager;
import net.runelite.client.eventbus.Subscribe;
//Java Libraries
import java.awt.*;
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
    //Config Inject
    @Inject
    private rlToolKitConfig config;
    //Config window
    @Provides
    rlToolKitConfig provideConfig(ConfigManager configManager){
        return configManager.getConfig(rlToolKitConfig.class);
    }

    //MenuManager
    @Inject
    private Provider<MenuManager> menuManager;
    //overlay
    @Inject
    private rlToolKitOverlay overlay;
    //Overlay Manager
    @Inject
    private OverlayManager overlayManager;
    @Inject
    @Nullable
    private Client client;

    //Tracked tile queue
    Queue<pointTime> ptQ = new LinkedList<>();
    //Tracking vars
    public Player trackedPlayer = null;
    public boolean tracking = false;
    public boolean ticking = false;
    public long tick = 0;

    //Custom Menu Entry
    private static final String ACQUIRE_TARGET = "Acquire Target";
    //StartUp Plugin
    @Override
    protected void startUp() throws Exception{
        menuManager.get().addPlayerMenuItem(ACQUIRE_TARGET);
        overlayManager.add(overlay);
    }

    //Clean up
    @Override
    protected void shutDown() throws Exception{
        overlayManager.remove(overlay);
        menuManager.get().removePlayerMenuItem(ACQUIRE_TARGET);
        ptQ.removeAll(ptQ);
        tracking=false;
        trackedPlayer=null;
        ticking=false;
    }
    //Logic for custom menu entry onClick()
    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event){
        if (event.getMenuAction() == MenuAction.RUNELITE_PLAYER && event.getMenuOption().equals(ACQUIRE_TARGET)){
            trackedPlayer = client.getCachedPlayers()[event.getId()];
            if (trackedPlayer == null){
                return;
            }else if (tracking == false){
                tracking = true;
                ticking=true;
                config.playerEntity() = trackedPlayer.toString();
            }else{
                tracking = false;
                ticking=false;
                ptQ.removeAll(ptQ);
            }
        }
    }

    @Subscribe
    public void onGameTick(GameTick event){
        if (tracking){
            tick++;
            WorldPoint local = trackedPlayer.getWorldLocation();
            tileCheck(local,tick);

        }
    }

    public void tileCheck(WorldPoint local,long t){
        if (ptQ.size()==0){
            ptQ.add(new pointTime(local,t));
        }else if(ptQ.peek().getLP()==local){
            ptQ.poll();
            ptQ.add(new pointTime(local,t));
        }else if (ptQ.size()>100){
            ptQ.remove();
            ptQ.add(new pointTime(local,t));
        }else{
            ptQ.add(new pointTime(local,t));
        }
    }

    //Method to test if animationID is interrupted by performing actions
    public int printAnimation(Player target){
        return target.getAnimation();
    }

}
