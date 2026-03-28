package pierceth.odm.gameassets;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import pierceth.odm.OdachiMoveset;
import pierceth.odm.api.animation.types.SimpleAttackAnimation;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;

@EventBusSubscriber(modid = OdachiMoveset.MODID)
public class OdachiAnimations {
    public static AnimationAccessor<SimpleAttackAnimation> ODACHI_AUTO1;
    public static AnimationAccessor<SimpleAttackAnimation> ODACHI_AUTO2;
    public static AnimationAccessor<SimpleAttackAnimation> ODACHI_AUTO3;
    public static AnimationAccessor<StaticAnimation> ODACHI_IDLE;
    public static AnimationAccessor<MovementAnimation> ODACHI_WALK;
    public static AnimationAccessor<MovementAnimation> ODACHI_RUN;
    public static AnimationAccessor<MovementAnimation> ODACHI_SNEAK;
    @SubscribeEvent
    public static void registerAnimations(AnimationManager.AnimationRegistryEvent event) {
        event.newBuilder("odm", OdachiAnimations::build);
    }
    private static void build(AnimationManager.AnimationBuilder builder) {
        Joint toolR = Armatures.BIPED.get().toolR;
        Joint toolL = Armatures.BIPED.get().toolR;
        Armatures.ArmatureAccessor<HumanoidArmature> BIPED = Armatures.BIPED;

        // Living Animations
        ODACHI_IDLE = builder.nextAccessor(livingAnimation("hold_odachi"), accessor -> new StaticAnimation(true, accessor, BIPED));
        ODACHI_WALK = builder.nextAccessor(livingAnimation("walk_odachi"), accessor -> new MovementAnimation(true, accessor, BIPED));
        ODACHI_RUN =  builder.nextAccessor(livingAnimation("run_odachi"), accessor -> new MovementAnimation(true, accessor, BIPED));
        ODACHI_SNEAK =  builder.nextAccessor(livingAnimation("sneak_odachi"), accessor -> new MovementAnimation(true, accessor, BIPED));

        // Combat Animations (Note that the timings bellow are NOT accurate, they're just a place-holder!)

        /* // remove this if you have the combat animations ready

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
            */ // remove this if you have the combat animations ready
    }

    private static String livingAnimation(String name) {
        return "biped/living/" + name;
    }
    private static String combatAnimation(String name) {
        return "biped/combat/" + name;
    }
}
