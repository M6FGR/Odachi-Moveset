package pierceth.odm.gameassets;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.api.registry.ItemsRegistry;
import pierceth.odm.world.item.OdachiItem;

public class OdachiItems implements ILoadableClass {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, OdachiMoveset.MODID);

    public static final DeferredHolder<Item, Item> ODACHI = ItemsRegistry.newItem("odachi", OdachiItem::new, ITEMS, properties -> properties.rarity(Rarity.RARE).durability(1980));
    @Override
    public void onModConstructor(IEventBus modBus) {
        ITEMS.register(modBus);
    }
}
