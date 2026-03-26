package pierceth.odm;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.client.tab.OdachiItemsTab;
import pierceth.odm.gameassets.OdachiItems;
import pierceth.odm.world.capabilities.item.WeaponCapabilityPreset;

@Mod(OdachiMoveset.MODID)
public class OdachiMoveset {
    public static final String MODID = "odm";
    public static final Logger LOGGER = LogManager.getLogger();
    public OdachiMoveset(IEventBus modBus, ModContainer modContainer) {
        modBus.addListener(this::addCreativeTab);
        ILoadableClass.loadClasses(modBus,
                WeaponCapabilityPreset.class,
                OdachiItemsTab.class,
                OdachiItems.class
        );
    }
    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
    }
}
