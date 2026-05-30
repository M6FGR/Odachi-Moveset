package pierceth.odm.client.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.gameassets.OdachiItems;

import java.util.function.Supplier;
public class OdachiCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OdachiMoveset.MODID);

    public static final Supplier<CreativeModeTab> ODM_TAB = CREATIVE_MODE_TABS.register("odm_tab", () -> CreativeModeTab.builder().icon(() ->
                    new ItemStack(OdachiItems.ODACHI.get()))
            .title(Component.translatable("creativetab.odm"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(OdachiItems.ODACHI.get());
                output.accept(OdachiItems.ROUND_SHIELD.get());
            })
            .build()
    );

}