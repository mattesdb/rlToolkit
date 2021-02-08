package net.runelite.client.plugins.rlToolKit;

import net.runelite.api.coords.LocalPoint;

//pointTime represents a point in 2D-xy as well as the game tick it was created on
public class pointTime {
    private LocalPoint lp;
    private int tick;

    //constructor
    public pointTime(LocalPoint x, int t){
        this.lp = x;
        this.tick = t;
    }
    //get the LocalPoint
    public LocalPoint getLP(){
        return this.lp;
    }
    //get the GameTick
    public int getTick(){
        return this.tick;
    }
}
