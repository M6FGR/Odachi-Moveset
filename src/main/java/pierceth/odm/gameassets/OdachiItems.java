package pierceth.odm.gameassets;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.api.registry.ItemsRegistry;

public class OdachiItems implements ILoadableClass {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, OdachiMoveset.MODID);

    public static final DeferredHolder<Item, Item> ODACHI = ItemsRegistry.newItem("odachi", Item::new, ITEMS);
    @Override
    public void onModConstructor(IEventBus modBus) {
        ITEMS.register(modBus);
    }
}
