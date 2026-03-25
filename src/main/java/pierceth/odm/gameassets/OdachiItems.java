package pierceth.odm.gameassets;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import pierceth.odm.api.registry.ItemsRegistry;

public class OdachiItems {
    public static final DeferredHolder<Item, Item> ODACHI = ItemsRegistry.newItem("odachi", Item::new);
}
