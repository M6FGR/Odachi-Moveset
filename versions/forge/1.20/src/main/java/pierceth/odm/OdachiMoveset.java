package pierceth.odm;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pierceth.odm.client.tab.OdachiCreativeTab;
import pierceth.odm.gameassets.OdachiItems;
import pierceth.odm.world.capabilities.item.WeaponCapabilityPresets;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

@Mod(OdachiMoveset.MODID)
public class OdachiMoveset {
    public static final String MODID = "odm";
    public static final Logger LOGGER = LogManager.getLogger();

    public OdachiMoveset(FMLJavaModLoadingContext context) {
        IEventBus modBus = context.getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        OdachiItems.ITEMS.register(modBus);
        OdachiCreativeTab.CREATIVE_MODE_TABS.register(modBus);

        WeaponCategory.ENUM_MANAGER.registerEnumCls(MODID, WeaponCapabilityPresets.OdachiCategories.class);
    }


}
