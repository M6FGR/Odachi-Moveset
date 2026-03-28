package pierceth.odm.client.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.gameassets.OdachiItems;

import java.util.function.Supplier;
public class OdachiItemsTab implements ILoadableClass {
    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OdachiMoveset.MODID);

    private static final Supplier<CreativeModeTab> ODM_TAB = CREATIVE_MODE_TABS.register("odm_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(OdachiItems.ODACHI.get()))
            .title(Component.translatable("creativetab.odm"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(OdachiItems.ODACHI.get());
                output.accept(OdachiItems.ROUND_SHIELD.get());
            })
            .build()
    );

    @Override
    public void onModConstructor(IEventBus modBus) {
        CREATIVE_MODE_TABS.register(modBus);
    }
}