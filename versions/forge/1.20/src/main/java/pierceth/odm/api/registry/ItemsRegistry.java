package pierceth.odm.api.registry;

import jdk.jfr.Experimental;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.item.EpicFightItems;

import java.util.function.Function;
import java.util.function.UnaryOperator;
@Experimental
public class ItemsRegistry {
    /**
     * Registers a new item with easy property manipulation.
     * @param itemId Unique identifier for the item.
     * @param factory The constructor reference (e.g., MyItem::new).
     * @param propertyModifier A lambda to adjust item properties (e.g., p -> p.stacksTo(1)).
     * @param itemRegistry the DeferredRegistry in your items class.
     */
    public static <T extends Item> RegistryObject<T> newItem(
            String itemId,
            Function<Item.Properties, T> factory,
            DeferredRegister<Item> itemRegistry,
            UnaryOperator<Item.Properties> propertyModifier
            ) {
        return itemRegistry.register(itemId, () -> {
            Item.Properties props = propertyModifier.apply(new Item.Properties());
            return factory.apply(props);
        });
    }

    // Overload for simple items that don't need custom properties
    public static <T extends Item> RegistryObject<T> newItem(String itemId, Function<Item.Properties, T> factory, DeferredRegister<Item> itemRegistry) {
        return newItem(itemId, factory, itemRegistry, p -> p);
    }
}