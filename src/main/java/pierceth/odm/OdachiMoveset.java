package pierceth.odm;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.client.tab.OdachiItemsTab;
import pierceth.odm.gameassets.OdachiItems;
import pierceth.odm.world.capabilities.item.WeaponCapabilityPreset;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod(OdachiMoveset.MODID)
public class OdachiMoveset {
    public static final String MODID = "odm";
    public static final Logger LOGGER = LogManager.getLogger();
    public OdachiMoveset(IEventBus modBus) {
        modBus.addListener(this::addCreativeTab);
        ILoadableClass.loadClasses(modBus,
                WeaponCapabilityPreset.class,
                OdachiItemsTab.class,
                OdachiItems.class
        );
        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, WeaponCapabilityPreset.OdachiCategories.class);
    }
    private void addCreativeTab(BuildCreativeModeTabContentsEvent event) {
    }
}
