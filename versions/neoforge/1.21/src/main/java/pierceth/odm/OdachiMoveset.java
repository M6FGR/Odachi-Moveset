package pierceth.odm;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pierceth.odm.client.tab.OdachiItemsTab;
import pierceth.odm.gameassets.OdachiItems;
import pierceth.odm.world.capabilities.item.WeaponCapabilityPresets;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod(OdachiMoveset.MODID)
public class OdachiMoveset {
    public static final String MODID = "odm";
    public static final Logger LOGGER = LogManager.getLogger();
    public OdachiMoveset(IEventBus modBus) {
        modBus.addListener(this::doCommonEvents);

        OdachiItems.ITEMS.register(modBus);
        OdachiItemsTab.CREATIVE_MODE_TABS.register(modBus);

        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, WeaponCapabilityPresets.OdachiCategories.class);
    }
    private void doCommonEvents(FMLCommonSetupEvent event) {
        event.enqueueWork(WeaponCapabilityPresets::registerCapability);
    }
}
