package pierceth.odm.world.capabilities.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.gameassets.OdachiAnimations;
import pierceth.odm.gameassets.OdachiColliders;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;
import yesman.epicfight.world.capabilities.item.ShieldCapability;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Function;
@Mod.EventBusSubscriber(
        modid = OdachiMoveset.MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD
)
public class WeaponCapabilityPresets {
    private static final Function<Item, CapabilityItem.Builder> ODACHI = (item) ->
            WeaponCapability.builder()
                    .styleProvider(entityPatch -> {
                           if (isInOffHand(entityPatch, CapabilityItem.WeaponCategories.SHIELD)) {
                               return Styles.ONE_HAND;
                           }
                           return Styles.TWO_HAND;
                    })
                    .newStyleCombo(
                            Styles.TWO_HAND,
                            Animations.UCHIGATANA_AUTO1,
                            Animations.UCHIGATANA_AUTO2,
                            Animations.UCHIGATANA_AUTO3,
                            Animations.UCHIGATANA_DASH,
                            Animations.UCHIGATANA_AIR_SLASH
                    )

                    .category(OdachiCategories.ODACHI)
                    .collider(OdachiColliders.ODACHI)
                    .swingSound(EpicFightSounds.WHOOSH.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())

                    .innateSkill(Styles.TWO_HAND, itemStack -> EpicFightSkills.TSUNAMI)
                    .livingMotionModifier(
                            Styles.TWO_HAND, LivingMotions.IDLE, OdachiAnimations.ODACHI_IDLE
                    )
                    .livingMotionModifier(
                            Styles.TWO_HAND, LivingMotions.WALK, OdachiAnimations.ODACHI_WALK
                    )
                    .livingMotionModifier(
                            Styles.TWO_HAND, LivingMotions.RUN, OdachiAnimations.ODACHI_RUN
                    )
                    .livingMotionModifier(
                            Styles.TWO_HAND, LivingMotions.KNEEL, OdachiAnimations.ODACHI_IDLE
                    )
                    .livingMotionModifier(
                            Styles.TWO_HAND, LivingMotions.SNEAK, OdachiAnimations.ODACHI_SNEAK
                    )
                    .newStyleCombo(
                            Styles.ONE_HAND,
                            Animations.LONGSWORD_AUTO1,
                            Animations.LONGSWORD_AUTO2,
                            Animations.LONGSWORD_AUTO3,
                            Animations.LONGSWORD_DASH,
                            Animations.LONGSWORD_AIR_SLASH

                    )
                    .livingMotionModifier(
                         Styles.ONE_HAND, LivingMotions.IDLE, OdachiAnimations.ODACHI_IDLE_ONEHAND
                    )
                    .livingMotionModifier(
                            Styles.ONE_HAND, LivingMotions.WALK, OdachiAnimations.ODACHI_WALK_ONEHAND
                    );

    private static final Function<Item, CapabilityItem.Builder> ROUND_SHIELD = item ->
            CapabilityItem.builder()
                    .constructor(RoundedShieldCapability::new)
                    .category(WeaponCategories.SHIELD);

    @SubscribeEvent
    public static void registerCapability(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath("odm", "odachi"), ODACHI);
        event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath("odm", "round_shield"), ROUND_SHIELD);
    }

    public enum OdachiCategories implements WeaponCategory {
        ODACHI(Component.translatable("weapon_category.odm.odachi"));
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

    }

    private static boolean isInOffHand(LivingEntityPatch<?> entityPatch, WeaponCategory category) {
        return entityPatch.getHoldingItemCapability(InteractionHand.OFF_HAND).getWeaponCategory() == category;
    }
}
