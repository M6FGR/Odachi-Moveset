package pierceth.odm.world.capabilities.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import pierceth.odm.api.cls.ILoadableClass;
import pierceth.odm.gameassets.OdachiAnimations;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.registry.entries.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.function.Function;

import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.MOUNT;
import static yesman.epicfight.world.capabilities.item.CapabilityItem.Styles.TWO_HAND;

public class WeaponCapabilityPreset implements ILoadableClass {
    public static final Function<Item, CapabilityItem.Builder<?>> ODACHI = (item) -> {
        return WeaponCapability.builder()
                .category(OdachiCategories.ODACHI)
                .styleProvider(playerPatch -> {
                    return TWO_HAND;
                })
                .hitSound(EpicFightSounds.BLADE_HIT.get())
                .canBePlacedOffhand(false)
                .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                .collider(ColliderPreset.TOOLS)
                .newStyleCombo(TWO_HAND, OdachiAnimations.ODACHI_AUTO1, OdachiAnimations.ODACHI_AUTO2, OdachiAnimations.ODACHI_AUTO1, Animations.AXE_DASH, Animations.AXE_AIRSLASH)
                .newStyleCombo(MOUNT, Animations.SWORD_MOUNT_ATTACK)
                .innateSkill(TWO_HAND, (itemstack) -> {
                    return EpicFightSkills.TSUNAMI.get();
                })
                .livingMotionModifier(TWO_HAND, LivingMotions.IDLE, OdachiAnimations.ODACHI_IDLE)
                .livingMotionModifier(TWO_HAND, LivingMotions.WALK, OdachiAnimations.ODACHI_WALK)
                .livingMotionModifier(TWO_HAND, LivingMotions.RUN, OdachiAnimations.ODACHI_RUN);
    };

    private void registerCapability() {
        EpicFightEventHooks.Registry.WEAPON_CAPABILITY_PRESET.registerEvent(event -> event.getTypeEntry()
                .put(ResourceLocation.fromNamespaceAndPath("odm", "odachi"), ODACHI), 1);
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

        @Override
        public Component getTranslatable() {
            return this.component;
        }
    }
    @Override
    public void onModConstructor(IEventBus modBus) {
        modBus.<FMLCommonSetupEvent>addListener(commonEvent -> {
            commonEvent.enqueueWork(this::registerCapability);
        });
    }
}
