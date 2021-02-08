package net.runelite.client.plugins.rlToolKit;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
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
                renderTile(graphics,pt.getLP(),pt.getTick());
            }
        }
        return null;
    }
    private void renderTile(final Graphics2D graphics, final LocalPoint dest, final String t) {
        if (dest == null) {
            return;
        }
        final Polygon poly = Perspective.getCanvasTilePoly(client, dest);
        if (poly == null) {
            return;
        }
        OverlayUtil.renderPolygon(graphics, poly, new Color(15,15,15,15));
    }
}
