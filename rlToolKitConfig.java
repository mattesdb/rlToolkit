package net.runelite.client.plugins.rlToolKit;


//Mandatory imports for Config
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

//Name the Config
@ConfigGroup("rlToolKit")
public interface rlToolKitConfig extends Config {
    //Configurations go here
    @ConfigItem
            (
                    position = 1,
                    keyName = "gm",
                    name = "GODMODE",
                    description = "never die to pkers again!"
            )
    default boolean booleanConfig() { return false; }
}
