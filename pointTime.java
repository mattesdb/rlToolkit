package net.runelite.client.plugins.rlToolKit;

import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;

//pointTime represents a point in 2D-xy as well as the game tick it was created on
public class pointTime {
    private WorldPoint lp;
    private long tick;
    private int potential;

    //constructor
    public pointTime(WorldPoint x, long t){
        this.lp = x;
        this.tick = t;
        this.potential=0;
    }
    //get the LocalPoint
    public WorldPoint getLP(){
        return this.lp;
    }
    public void incPot(){
        this.potential++;
    }
    public int getPot(){
        return potential;
    }
    //get the GameTick
    public long getTick(){
        return this.tick;
    }
}
