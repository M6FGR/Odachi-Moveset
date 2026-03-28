package pierceth.odm.api.registry;

import com.google.common.collect.ImmutableMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.particle.HitParticleType;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.Style;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.util.Map;
import java.util.function.Function;

@Experimental
public class WeaponCapabilityRegistry {
    private final WeaponCapability.Builder builder;
    protected Collider currentCollider;
    protected WeaponCategory currentCategory;
    protected SoundEvent currentSwingSound;
    protected SoundEvent currentHitSound;
    protected @Nullable Skill currentPassive;
    protected @Nullable Skill currentInnate;
    protected boolean currentHoldableInOffHand;
    protected AnimationAccessor<? extends AttackAnimation>[] currentCombo;
    private Style currentStyle = CapabilityItem.Styles.COMMON;

    private WeaponCapabilityRegistry() {
        this.builder = WeaponCapability.builder();
    }

    public static WeaponCapabilityRegistry builder() {
        return new WeaponCapabilityRegistry();
    }


    // --- Preset & Style Methods ---

    @SafeVarargs
    public final WeaponCapabilityRegistry newPreset(Style style, WeaponCategory category, Collider collider, SoundEvent swingSound, SoundEvent hitSound, HitParticleType hitParticleType, boolean holdableInOffHand, @Nullable Skill passiveSkill, @Nullable Skill innateSkill, AnimationAccessor<? extends AttackAnimation>... animations) {
        this.currentCombo = animations;
        this.currentInnate = innateSkill;
        this.currentPassive = passiveSkill;
        this.currentStyle = style;
        this.currentCategory = category;
        this.currentSwingSound = swingSound;
        this.currentHitSound = hitSound;
        this.currentHoldableInOffHand = holdableInOffHand;
        this.currentCollider = collider;
        this.builder
                .category(category)
                .collider(collider)
                .hitSound(hitSound)
                .swingSound(swingSound)
                .hitParticle(hitParticleType)
                .canBePlacedOffhand(holdableInOffHand)
                .passiveSkill(passiveSkill)
                .innateSkill(style, itemStack -> innateSkill)
                .weaponCombinationPredicator(entityPatch -> true)
                .newStyleCombo(style, animations);
        return this;
    }

    @SafeVarargs
    public final WeaponCapabilityRegistry newStylePreset(Style style, @Nullable Skill passiveSkill, @Nullable Skill innateSkill, AnimationAccessor<? extends AttackAnimation>... animations) {
        this.currentCombo = animations;
        this.currentInnate = innateSkill;
        this.currentPassive = passiveSkill;
        this.currentStyle = style;
        this.builder.category(currentCategory).collider(currentCollider).hitSound(currentHitSound).swingSound(currentSwingSound)
                .canBePlacedOffhand(currentHoldableInOffHand).passiveSkill(passiveSkill)
                .weaponCombinationPredicator(entityPatch -> currentHoldableInOffHand)
                .innateSkill(style, itemStack -> innateSkill)
                .newStyleCombo(style, animations);
        return this;
    }

    public final WeaponCapabilityRegistry anotherStyle(Style style, @Nullable Skill passiveSkill, @Nullable Skill innateSkill) {
        this.currentStyle = style;
        this.builder.category(currentCategory).collider(currentCollider).hitSound(currentHitSound).swingSound(currentSwingSound)
                .canBePlacedOffhand(currentHoldableInOffHand).passiveSkill(passiveSkill)
                .weaponCombinationPredicator(entityPatch -> currentHoldableInOffHand)
                .innateSkill(style, itemStack -> innateSkill)
                .newStyleCombo(style, currentCombo);
        return this;
    }

    public final WeaponCapabilityRegistry anotherStyle(Style style, @Nullable Skill innateSkill) {
        this.currentStyle = style;
        this.builder.category(currentCategory).collider(currentCollider).hitSound(currentHitSound).swingSound(currentSwingSound)
                .canBePlacedOffhand(currentHoldableInOffHand).passiveSkill(currentPassive)
                .weaponCombinationPredicator(entityPatch -> currentHoldableInOffHand)
                .innateSkill(style, itemStack -> innateSkill)
                .newStyleCombo(style, currentCombo);
        return this;
    }

    // --- Shield Methods ---

    public WeaponCapabilityRegistry newShieldPreset(WeaponCategory category) {
        this.builder.constructor(BasicShieldCapability::new);
        this.builder.category(category);
        return this;
    }

    public WeaponCapabilityRegistry newShieldPreset(WeaponCategory category, Function<WeaponCapability.Builder, CapabilityItem> constructor) {
        this.builder.constructor(constructor);
        this.builder.category(category);
        return this;
    }

    public WeaponCapabilityRegistry withShieldBlockAnimation(AnimationAccessor<? extends StaticAnimation> animation) {
        this.builder.constructor(builder -> {
            BasicShieldCapability shield = new BasicShieldCapability(builder);
            shield.animation = animation;
            return shield;
        });
        return this;
    }


    public WeaponCapabilityRegistry withStyleConditions(Function<LivingEntityPatch<?>, Style> styleProvider) {
        this.builder.styleProvider(styleProvider);
        return this;
    }

    public WeaponCapabilityRegistry withLivingMotion(LivingMotion livingMotion, AnimationAccessor<? extends StaticAnimation> animation) {
        this.builder.livingMotionModifier(this.currentStyle, livingMotion, animation);
        return this;
    }

    public WeaponCapabilityRegistry forEachMotion(Object... pairs) {
        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("forEachMotion must have an even number of arguments (Motion/Animation pairs)!");
        }
        for (int i = 0; i < pairs.length; i += 2) {
            LivingMotion motion = (LivingMotion) pairs[i];
            @SuppressWarnings("unchecked")
            AnimationAccessor<? extends StaticAnimation> animation =
                    (AnimationAccessor<? extends StaticAnimation>) pairs[i + 1];

            this.withLivingMotion(motion, animation);
        }
        return this;
    }

    public WeaponCapabilityRegistry withOffHandPredict(Function<LivingEntityPatch<?>, Boolean> predict) {
        this.builder.weaponCombinationPredicator(predict);
        return this;
    }

    public WeaponCapabilityRegistry withOffHandPredict(boolean predict) {
        this.builder.weaponCombinationPredicator(entityPatch -> predict);
        return this;
    }




    // --- The Build Method ---

    public WeaponCapability.Builder build() {
        return this.builder;
    }

    // --- Shield Capability Class ---

    static class BasicShieldCapability extends CapabilityItem {
        private AnimationAccessor<? extends StaticAnimation> animation = Animations.BIPED_BLOCK;

        public BasicShieldCapability(Builder<?> builder) {
            super(builder);
        }

        @Override
        public Map<LivingMotion, AnimationAccessor<? extends StaticAnimation>> getLivingMotionModifier(LivingEntityPatch<?> playerpatch, InteractionHand hand) {
            return ImmutableMap.of(LivingMotions.BLOCK_SHIELD, this.animation);
        }
    }
}