package pierceth.odm.api.animation.types;

import pierceth.odm.api.animation.SimpleAnimationProperty;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class SimpleMovementAnimation extends MovementAnimation {
    public SimpleMovementAnimation(boolean isRepeat, AnimationAccessor<? extends MovementAnimation> accessor, AssetAccessor<? extends Armature> armature) {
        super(isRepeat, accessor, armature);
    }

    public SimpleMovementAnimation(float transitionTime, boolean isRepeat, AnimationAccessor<? extends MovementAnimation> accessor, AssetAccessor<? extends Armature> armature) {
        super(transitionTime, isRepeat, accessor, armature);
    }

    public SimpleMovementAnimation(float transitionTime, boolean isRepeat, String path, AssetAccessor<? extends Armature> armature) {
        super(transitionTime, isRepeat, path, armature);
    }

    @Override
    public float getPlaySpeed(LivingEntityPatch<?> entitypatch, DynamicAnimation animation) {
        if (this.properties.containsKey(SimpleAnimationProperty.PLAY_SPEED)) {
            return this.getProperty(SimpleAnimationProperty.PLAY_SPEED).orElse(1.0F);
        }
        return super.getPlaySpeed(entitypatch, animation);
    }
}
