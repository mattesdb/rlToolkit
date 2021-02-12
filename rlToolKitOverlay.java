package net.runelite.client.plugins.rlToolKit;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.rlToolKit.rlToolKitConfig;
import net.runelite.client.ui.overlay.*;
import javax.inject.Inject;
import java.awt.*;
import java.util.Queue;
public class rlToolKitOverlay extends Overlay {
    //Client and plugin Config
    private final Client client;
    private final rlToolKitConfig config;
    private final rlToolKitPlugin plugin;
    @Inject
    private rlToolKitOverlay(Client client, rlToolKitConfig config, rlToolKitPlugin plugin){
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.MED);
    }
    public Dimension render(Graphics2D graphics){
        if(plugin.tracking==true){
            for(pointTime pt:plugin.ptQ){
                if (plugin.tick - pt.getTick() < 50){
                    renderTile(graphics,pt.getLP(),new Color(36,238,36,1));
                }else if(plugin.tick - pt.getTick() < 75){
                    renderTile(graphics,pt.getLP(),new Color(238,137,36,1));
                }else{
                    renderTile(graphics,pt.getLP(),new Color(158,10,10,1));
            }
            }
        }
        return null;
    }
    private void renderTile(final Graphics2D graphics, final WorldPoint dest, Color c){
        if (dest == null) {
            return;
        }
        final Polygon poly = Perspective.getCanvasTilePoly(client, new LocalPoint(dest.getX(),dest.getY()));
        if (poly == null) {
            return;
        }
        OverlayUtil.renderPolygon(graphics, poly, c);
    }
}
