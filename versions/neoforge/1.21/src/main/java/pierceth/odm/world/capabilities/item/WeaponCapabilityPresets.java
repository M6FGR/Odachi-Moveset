package pierceth.odm.world.capabilities.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.api.registry.WeaponCapabilityRegistry;
import pierceth.odm.gameassets.OdachiAnimations;
import pierceth.odm.gameassets.OdachiColliders;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.registry.entries.EpicFightParticles;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.ShieldCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Function;

public class WeaponCapabilityPresets implements ILoadableClass {
    private final Function<Item, CapabilityItem.Builder<?>> ODACHI = (item) ->
            WeaponCapabilityRegistry.builder()
                    .withStyleConditions(entityPatch -> {
                           if (this.isInOffHand(entityPatch, OdachiCategories.ROUND_SHIELD)) {
                               return Styles.ONE_HAND;
                           }
                           return Styles.TWO_HAND;
                    })
                    .newPreset(
                            Styles.TWO_HAND,
                            OdachiCategories.ODACHI,
                            OdachiColliders.ODACHI,
                            EpicFightSounds.WHOOSH.get(),
                            EpicFightSounds.BLADE_HIT.get(),
                            EpicFightParticles.HIT_BLADE.get(),
                            false,
                            null,
                            EpicFightSkills.TSUNAMI.get(),
                            Animations.UCHIGATANA_AUTO1,
                            Animations.UCHIGATANA_AUTO2,
                            Animations.UCHIGATANA_AUTO3,
                            Animations.UCHIGATANA_DASH,
                            Animations.UCHIGATANA_AIR_SLASH
                    )
                    .forEachMotion(
                            LivingMotions.IDLE, OdachiAnimations.ODACHI_IDLE,
                            LivingMotions.WALK, OdachiAnimations.ODACHI_WALK,
                            LivingMotions.RUN, OdachiAnimations.ODACHI_RUN,
                            LivingMotions.KNEEL, OdachiAnimations.ODACHI_IDLE,
                            LivingMotions.SNEAK, OdachiAnimations.ODACHI_SNEAK
                    )
                    .newStylePreset(
                            Styles.ONE_HAND,
                            null,
                            EpicFightSkills.SHARP_STAB.get(),
                            Animations.LONGSWORD_AUTO1,
                            Animations.LONGSWORD_AUTO2,
                            Animations.LONGSWORD_AUTO3,
                            Animations.LONGSWORD_DASH,
                            Animations.LONGSWORD_AIR_SLASH

                    )
                    .forEachMotion(
                            LivingMotions.IDLE, OdachiAnimations.ODACHI_IDLE_ONEHAND,
                            LivingMotions.WALK, Animations.BIPED_WALK_LONGSWORD
                    )
                    .build();

    private final Function<Item, CapabilityItem.Builder<?>> ROUNDER_SHIELD = item ->
            WeaponCapabilityRegistry.builder()
                    .newShieldPreset(OdachiCategories.ROUND_SHIELD)
                    .build();


    private void registerCapability() {
        EpicFightEventHooks.Registry.WEAPON_CAPABILITY_PRESET.registerEvent(event -> {
                event.getTypeEntry()
                        .put(ResourceLocation.fromNamespaceAndPath("odm", "odachi"), this.ODACHI);
                event.getTypeEntry()
                        .put(ResourceLocation.fromNamespaceAndPath("odm", "round_shield"), this.ROUNDER_SHIELD);
        }, 1);
    }

    public enum OdachiCategories implements WeaponCategory {
        ODACHI(Component.translatable("weapon_category.odm.odachi")),
        ROUND_SHIELD(Component.translatable("weapon_category.odm.round_shield"));
        final int id;
        final Component component;

        OdachiCategories(Component component) {
            this.id = WeaponCategory.ENUM_MANAGER.assign(this);
            this.component = component;
        }
        @Override
        public int universalOrdinal() {
            return this.id;
        }

        @Override
        public Component getTranslatable() {
            return this.component;
        }
    }

    private boolean isInOffHand(LivingEntityPatch<?> entityPatch, WeaponCategory category) {
        return entityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == category;
    }
    @Override
    public void onModConstructor(IEventBus modBus) {
        modBus.<FMLCommonSetupEvent>addListener(commonEvent -> {
            commonEvent.enqueueWork(this::registerCapability);
        });
    }
}
