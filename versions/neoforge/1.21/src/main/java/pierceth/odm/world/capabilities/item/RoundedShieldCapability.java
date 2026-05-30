package pierceth.odm.world.capabilities.item;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.LivingMotion;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.Map;

public class RoundedShieldCapability extends CapabilityItem {
    public RoundedShieldCapability(Builder builder) {
        super(builder);
    }

    @Override
    public Map<LivingMotion, AnimationAccessor<? extends StaticAnimation>> getLivingMotionModifier(LivingEntityPatch<?> playerpatch, InteractionHand hand) {
        return ImmutableMap.of(LivingMotions.BLOCK_SHIELD, Animations.BIPED_BLOCK);
    }
}
