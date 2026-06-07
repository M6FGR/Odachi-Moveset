package pierceth.odm;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pierceth.odm.client.tab.OdachiItemsTab;
import pierceth.odm.gameassets.OdachiItems;
import pierceth.odm.world.capabilities.item.OdachiCategories;
import pierceth.odm.world.capabilities.item.OdachiItemPresets;
import pierceth.odm.world.capabilities.item.OdachiMovesets;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod(OdachiMoveset.MODID)
public class OdachiMoveset {
    public static final String MODID = "odm";
    public static final Logger LOGGER = LogManager.getLogger();
    public OdachiMoveset(IEventBus modBus) {

        OdachiItems.ITEMS.register(modBus);
        OdachiItemsTab.CREATIVE_MODE_TABS.register(modBus);
        OdachiMovesets.MOVESETS.register(modBus);
        OdachiItemPresets.PRESETS.register(modBus);

        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, OdachiCategories.class);
    }

}
