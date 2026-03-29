package pierceth.odm.gameassets;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.api.registry.ItemsRegistry;
import pierceth.odm.world.item.OdachiItem;

public class OdachiItems implements ILoadableClass {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, OdachiMoveset.MODID);

    public static final RegistryObject<OdachiItem> ODACHI = ItemsRegistry.newItem("odachi", OdachiItem::new, ITEMS, properties -> properties.rarity(Rarity.RARE).durability(1980));

    public static final RegistryObject<ShieldItem> ROUND_SHIELD = ItemsRegistry.newItem("round_shield", ShieldItem::new, ITEMS, properties -> properties.rarity(Rarity.UNCOMMON).durability(1980));

    @Override
    public void onModConstructor(IEventBus modBus) {
        ITEMS.register(modBus);
    }
}
