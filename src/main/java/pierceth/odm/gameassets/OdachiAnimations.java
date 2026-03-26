package pierceth.odm.gameassets;

import net.minecraft.world.phys.Vec3;
import pierceth.odm.api.animation.types.SimpleAttackAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;
// add a @EventBusSubscriber(modid = OdachiMoveset.MODID) here
public class OdachiAnimations {
    public static AnimationAccessor<SimpleAttackAnimation> ODACHI_AUTO1;
    public static AnimationAccessor<SimpleAttackAnimation> ODACHI_AUTO2;
    public static AnimationAccessor<SimpleAttackAnimation> ODACHI_AUTO3;
    public static AnimationAccessor<StaticAnimation> ODACHI_IDLE;
    public static AnimationAccessor<MovementAnimation> ODACHI_WALK;
    public static AnimationAccessor<MovementAnimation> ODACHI_RUN;
    // add a @SubscriberEvent here
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder("odm", OdachiAnimations::build);
    }

    private static void build(AnimationManager.AnimationBuilder builder) {
        Joint toolR = Armatures.BIPED.get().toolR;
        Joint toolL = Armatures.BIPED.get().toolR;
        Armatures.ArmatureAccessor<HumanoidArmature> BIPED = Armatures.BIPED;

        // Living Animations
       ODACHI_IDLE = builder.nextAccessor(livingAnimation("odachi_idle"), accessor -> new StaticAnimation(true, accessor, BIPED));
       ODACHI_WALK = builder.nextAccessor(livingAnimation("odachi_walk"), accessor -> new MovementAnimation(true, accessor, BIPED));
       ODACHI_RUN =  builder.nextAccessor(livingAnimation("odachi_run"), accessor -> new MovementAnimation(true, accessor, BIPED));

       // Combat Animations
        ODACHI_AUTO1 = builder.nextAccessor(combatAnimation("odachi_auto1"), accessor ->
                new SimpleAttackAnimation(
                        // convertTime
                        0.1F,
                        // antic
                        0.2F,
                        // preDelay (FPS)
                        15,
                        // contact (FPS)
                        25,
                        // recovery
                        0.8F,
                        null,
                        toolR,
                        accessor, BIPED
                )
                // you can add trails here, no files are required
                .addTrail("Tool_R", SimpleAttackAnimation.TrailColor.newColor(1, 1, 1), SimpleAttackAnimation.TrailPreset.newPreset(new Vec3(0.0F, 0.0F, 0.2F), new Vec3(0.0F, 0.2F, -1.2F), 3, 6)));
        ODACHI_AUTO2 = builder.nextAccessor(combatAnimation("odachi_auto2"), accessor ->
                new SimpleAttackAnimation(
                        0.1F,
                        0.2F,
                        30,
                        45,
                        0.8F,
                        null,
                        toolR,
                        accessor, BIPED
                )
                .addTrail("Tool_R", SimpleAttackAnimation.TrailColor.IRON));
        ODACHI_AUTO3 = builder.nextAccessor(combatAnimation("odachi_auto3"), accessor ->
                new SimpleAttackAnimation(
                        0.1F,
                        0.2F,
                        50,
                        65,
                        0.8F,
                        null,
                        toolR,
                        accessor, BIPED
                )
                .addTrail("Tool_R", SimpleAttackAnimation.TrailColor.IRON));
    }


    private static String livingAnimation(String name) {
        return "biped/living/" + name;
    }
    private static String combatAnimation(String name) {
        return "biped/combat/" + name;
    }
}
