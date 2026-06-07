package pierceth.odm.client.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.gameassets.OdachiItems;
import pierceth.odm.world.item.OdachiItem;

public class OdachiItemsTab  {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OdachiMoveset.MODID);

    static {
        CREATIVE_MODE_TABS.register("odm_tab", () -> CreativeModeTab.builder().icon(() ->
                        itemOf(OdachiItems.ROUND_SHIELD.get()))
                .title(Component.translatable("creativetab.odm"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(OdachiItems.ODACHI.get());
                    output.accept(OdachiItems.ROUND_SHIELD.get());
                })
                .build()
        );
    }

    private static ItemStack itemOf(Item item) {
        return new ItemStack(item);
    }

}