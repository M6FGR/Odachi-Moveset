package pierceth.odm.world.capabilities.item;

import pierceth.odm.OdachiMoveset;
import yesman.epicfight.registry.deferred.ItemPresetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredWeapon;
import yesman.epicfight.registry.entries.EpicFightProviderConditionals;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

public class OdachiItemPresets {
    public static final ItemPresetRegister PRESETS = ItemPresetRegister.create(OdachiMoveset.MODID);

    public static final DeferredWeapon ODACHI = PRESETS.registerWeapon("odachi", () ->
            WeaponCapability.builder()
                    .hitSound(EpicFightSounds.BLADE_HIT)
                    .swingSound(EpicFightSounds.WHOOSH)
                    .category(OdachiCategories.ODACHI)
                    .collider(OdachiColliders.ODACHI)
                    .addConditionals(EpicFightProviderConditionals.DEFAULT_2H_WIELD_STYLE, EpicFightProviderConditionals.SHIELD_OFFHAND)
                    .addMoveset(Styles.TWO_HAND, OdachiMovesets.ODACHI_2H)
                    .addMoveset(Styles.ONE_HAND, OdachiMovesets.ODACHI_1H)
    );

    public static final DeferredWeapon ROUND_SHIELD = PRESETS.registerWeapon("round_shield", () ->
            WeaponCapability.builder()
                    .category(WeaponCategories.SHIELD)
                    .constructor(RoundedShieldCapability::new)
    );
}
