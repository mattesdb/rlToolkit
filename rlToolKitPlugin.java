package net.runelite.client.plugins.rlToolKit;


//Mandatory imports
import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.OverlayManager;

//Plugin description
@PluginDescriptor(
        name = "rlToolKit",
        description = "Set of customized tools for the boys",
        tags = {"rl", "Tool", "Kit", "rlToolKit", "CRPL:"}
)

public class rlToolKitPlugin extends Plugin{
    @Inject
    private rlToolKitConfig config;

    @Provides
    rlToolKitConfig provideConfig(ConfigManager configManager){
        return configManager.getConfig(rlToolKitConfig.class);
    }

    @Override
    protected void startUp() throws Exception{

    }


}
