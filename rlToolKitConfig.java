package net.runelite.client.plugins.rlToolKit;


//Mandatory imports for Config
import net.runelite.api.Actor;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

//Name the Config
@ConfigGroup("rlToolKit")
public interface rlToolKitConfig extends Config {

    //Configurations go here
    @ConfigItem
            (

                    position = 0,
                    keyName = "gm",
                    name = "GODMODE",
                    description = "never die to pkers again!"
            )
    default boolean booleanConfig() { return false; }

    //Drop Party configurations (WIP)
    @ConfigSection
            (
                    name = "Dropper Tracker (WIP)",
                    description = "Drop party player tracking",
                    position = 1

            )
    String dropParySection = "dropPartySection";
    @ConfigItem
            (
                    position = 1,
                    keyName =  "trackedEntity",
                    name = "Player",
                    description = "displays the entityID of the tracked player",
                    section = "dropPartySection"

            )
    default String playerEntity() { return null; }
    @ConfigItem
            (
                    position = 2,
                    keyName = "tileC1",
                    name = "Start Tile Gradient",
                    description = "Colour Gradient Start",
                    section = "dropPartySection"
            )
    default int gradientStart(){return 0;}
    @ConfigItem
            (
                    position = 3,
                    keyName = "tileC2",
                    name = "End Tile Gradient",
                    description = "Colour Gradient End",
                    section = "dropPartySection"
            )
    default int gradientEnd(){return 0;}

    //Player Intention (WIP)
    @ConfigSection
            (
                    name = "Player Intention (WIP)",
                    description = "limited player intention visualization",
                    position = 2
            )
    String playerIntentionSection = "playerIntentionSection";
    @ConfigItem
            (
                    position = 1,
                    keyName = "Player",
                    name = "Player",
                    description = "displays the entityID of the tracked player",
                    section = "playerIntentionSection"
            )
    default String playerIntention() {return null;}
}
