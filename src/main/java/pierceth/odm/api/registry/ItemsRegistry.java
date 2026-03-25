package pierceth.odm.api.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.api.cls.ILoadableClass;
import yesman.epicfight.world.item.WeaponItem;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ItemsRegistry implements ILoadableClass {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, OdachiMoveset.MODID);

    public static DeferredHolder<Item, Item> ODACHI = newItem("odachi", Item::new);
    /**
     * Registers a new item with easy property manipulation.
     * @param itemId Unique identifier for the item.
     * @param factory The constructor reference (e.g., MyItem::new).
     * @param propertyModifier A lambda to adjust item properties (e.g., p -> p.stacksTo(1)).
     */
    public static <T extends Item> DeferredHolder<Item, T> newItem(
            String itemId,
            Function<Item.Properties, T> factory,
            UnaryOperator<Item.Properties> propertyModifier
    ) {
        return ITEMS.register(itemId, () -> {
            Item.Properties props = propertyModifier.apply(new Item.Properties());
            return factory.apply(props);
        });
    }

    // Overload for simple items that don't need custom properties
    public static <T extends Item> DeferredHolder<Item, T> newItem(String itemId, Function<Item.Properties, T> factory) {
        return newItem(itemId, factory, p -> p);
    }

    @Override
    public void onModConstructor(IEventBus modBus) {
        ITEMS.register(modBus);
    }
}