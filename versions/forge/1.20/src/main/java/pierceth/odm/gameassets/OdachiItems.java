package pierceth.odm.gameassets;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.world.item.OdachiItem;

public class OdachiItems  {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, OdachiMoveset.MODID);

    public static final RegistryObject<OdachiItem> ODACHI = ITEMS.register("odachi", () -> new OdachiItem(new Properties().rarity(Rarity.RARE).durability(1983)));

    public static final RegistryObject<ShieldItem> ROUND_SHIELD = ITEMS.register("round_shield", () -> new ShieldItem(new Properties().rarity(Rarity.UNCOMMON).durability(1980)));


}
