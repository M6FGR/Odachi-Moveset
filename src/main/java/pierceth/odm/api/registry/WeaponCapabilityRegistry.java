package pierceth.odm.api.registry;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.ApiStatus.Experimental;
import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.animation.AnimationManager;
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

    @SafeVarargs
    public final WeaponCapabilityRegistry newPreset(
            Style style,
            WeaponCategory category,
            Collider collider,
            SoundEvent swingSound,
            SoundEvent hitSound,
            boolean holdableInOffHand,
            @Nullable Skill passiveSkill,
            @Nullable Skill innateSkill,
            AnimationAccessor<? extends AttackAnimation>... animations)
    {
        this.currentCombo = animations;
        this.currentInnate = innateSkill;
        this.currentPassive = passiveSkill;
        this.currentStyle = style;
        this.currentCategory = category;
        this.currentSwingSound = swingSound;
        this.currentHitSound = hitSound;
        this.currentHoldableInOffHand = holdableInOffHand;
        this.currentCollider = collider;
        this.builder.category(category).collider(collider).hitSound(hitSound).swingSound(swingSound)
                .canBePlacedOffhand(holdableInOffHand).passiveSkill(passiveSkill)
                .innateSkill(style, itemStack -> innateSkill)
                .newStyleCombo(style, animations);
        return this;
    }
    @SafeVarargs
    public final WeaponCapabilityRegistry newStylePreset(
            Style style,
            @Nullable Skill passiveSkill,
            @Nullable Skill innateSkill,
            AnimationAccessor<? extends AttackAnimation>... animations)
    {
        this.currentCombo = animations;
        this.currentInnate = innateSkill;
        this.currentPassive = passiveSkill;
        this.currentStyle = style;
        this.builder.category(currentCategory)
                .collider(currentCollider)
                .hitSound(currentHitSound)
                .swingSound(currentSwingSound)
                .canBePlacedOffhand(currentHoldableInOffHand)
                .passiveSkill(passiveSkill)
                .weaponCombinationPredicator(entityPatch -> currentHoldableInOffHand)
                .innateSkill(style, itemStack -> innateSkill)
                .newStyleCombo(style, animations);
        return this;
    }
    public final WeaponCapabilityRegistry anotherStyle(
            Style style,
            @Nullable Skill passiveSkill,
            @Nullable Skill innateSkill
    )

    {
        this.currentStyle = style;
        this.builder.category(currentCategory)
                .collider(currentCollider)
                .hitSound(currentHitSound)
                .swingSound(currentSwingSound)
                .canBePlacedOffhand(currentHoldableInOffHand)
                .passiveSkill(passiveSkill)
                .weaponCombinationPredicator(entityPatch -> currentHoldableInOffHand)
                .innateSkill(style, itemStack -> innateSkill)
                .newStyleCombo(style, currentCombo);
        return this;
    }
    public final WeaponCapabilityRegistry anotherStyle(
            Style style,
            @Nullable Skill innateSkill
    )

    {
        this.currentStyle = style;
        this.builder.category(currentCategory)
                .collider(currentCollider)
                .hitSound(currentHitSound)
                .swingSound(currentSwingSound)
                .canBePlacedOffhand(currentHoldableInOffHand)
                .passiveSkill(currentPassive)
                .weaponCombinationPredicator(entityPatch -> currentHoldableInOffHand)
                .innateSkill(style, itemStack -> innateSkill)
                .newStyleCombo(style, currentCombo);
        return this;
    }

    public WeaponCapabilityRegistry newShieldPreset(
            WeaponCategory category
    ) {
        this.builder.constructor(BasicShieldCapability::new);
        this.builder.category(category);
        return this;
    }
    public  WeaponCapabilityRegistry newShieldPreset(
            WeaponCategory category,
            Function<WeaponCapability.Builder, CapabilityItem> constructor

    ) {
        this.builder.constructor(constructor);
        this.builder.category(category);
        return this;
    }

    public WeaponCapabilityRegistry withShieldBlockAnimation(AnimationAccessor<? extends StaticAnimation> animation) {
        BasicShieldCapability shieldCapability = new BasicShieldCapability();
        shieldCapability.animation = animation;
        return this;
    }


    public WeaponCapabilityRegistry withStyle(Style style) {
        this.currentStyle = style;
        return this;
    }


    public WeaponCapabilityRegistry withStyleConditions(Function<LivingEntityPatch<?>, Style> styleProvider) {
        this.builder.styleProvider(styleProvider);
        return this;
    }

    public WeaponCapabilityRegistry withCategory(WeaponCategory category) {
        this.builder.category(category);
        return this;
    }

    @SafeVarargs
    public final WeaponCapabilityRegistry withCombo(AnimationManager.AnimationAccessor<? extends AttackAnimation>... animation) {
        this.builder.newStyleCombo(this.currentStyle, animation);
        return this;
    }
    @SafeVarargs
    public final WeaponCapabilityRegistry withCombo(Style style, AnimationManager.AnimationAccessor<? extends AttackAnimation>... animation) {
        this.builder.newStyleCombo(style, animation);
        return this;
    }

    public WeaponCapabilityRegistry withLivingMotion(LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation) {
        this.builder.livingMotionModifier(this.currentStyle, livingMotion, animation);
        return this;
    }
    public WeaponCapabilityRegistry withLivingMotion(Style style,LivingMotion livingMotion, AnimationManager.AnimationAccessor<? extends StaticAnimation> animation) {
        this.builder.livingMotionModifier(style, livingMotion, animation);
        return this;
    }

    public WeaponCapabilityRegistry withInnateSkill(Skill innateSkill) {
        this.builder.innateSkill(this.currentStyle, itemStack -> innateSkill);
        return this;
    }

    public WeaponCapabilityRegistry withInnateSkill(Style style, Skill innateSkill) {
        this.builder.innateSkill(style, itemStack -> innateSkill);
        return this;
    }

    public WeaponCapabilityRegistry withInnateSkill(Function<ItemStack, Skill> innateSkillProvider) {
        this.builder.innateSkill(this.currentStyle, innateSkillProvider);
        return this;
    }

    public WeaponCapabilityRegistry withPassiveSkill(Skill passiveSkill) {
        this.builder.passiveSkill(passiveSkill);
        return this;
    }

    public WeaponCapabilityRegistry withSwingSound(Holder<SoundEvent> swingSound) {
        this.builder.swingSound(swingSound.value());
        return this;
    }

    public WeaponCapabilityRegistry withHitSound(SoundEvent hitSound) {
        this.builder.hitSound(hitSound);
        return this;
    }

    public WeaponCapabilityRegistry withHitSound(Holder<SoundEvent> hitSound) {
        this.builder.hitSound(hitSound.value());
        return this;
    }

    public WeaponCapabilityRegistry withHitParticle(HitParticleType hitParticle) {
        this.builder.hitParticle(hitParticle);
        return this;
    }

    public WeaponCapabilityRegistry holdableInOffHand(boolean val) {
        this.builder.canBePlacedOffhand(val);
        return this;
    }

    public WeaponCapabilityRegistry withReach(float reach) {
        this.builder.reach(reach);
        return this;
    }

    public WeaponCapabilityRegistry offHandPredict(boolean predict) {
        this.builder.weaponCombinationPredicator(entityPatch -> predict);
        return this;
    }
    public WeaponCapabilityRegistry offHandPredictParameterized(Function<LivingEntityPatch<?>, Boolean> predict) {
        this.builder.weaponCombinationPredicator(predict);
        return this;
    }

    public WeaponCapabilityRegistry withCollider(Collider collider) {
        this.builder.collider(collider);
        return this;
    }

    public WeaponCapability.Builder build() {
        return this.builder;
    }

    static class BasicShieldCapability extends CapabilityItem {
        private AnimationAccessor<? extends StaticAnimation> animation = Animations.BIPED_BLOCK;
        public BasicShieldCapability(Builder<?> builder) {
            super(builder);
        }

        public BasicShieldCapability() {
            super(CapabilityItem.builder());
        }

        @Override
        public Map<LivingMotion, AnimationAccessor<? extends StaticAnimation>> getLivingMotionModifier(LivingEntityPatch<?> playerpatch, InteractionHand hand) {
            return ImmutableMap.of(LivingMotions.BLOCK_SHIELD, this.animation);
        }
    }
}