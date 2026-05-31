package pierceth.odm.world.capabilities.item;

import pierceth.odm.OdachiMoveset;
import pierceth.odm.gameassets.OdachiAnimations;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.ex_cap.data.Moveset;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.registry.deferred.MovesetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;

public class OdachiMovesets {
    public static final MovesetRegister MOVESETS = MovesetRegister.create(OdachiMoveset.MODID);

    public static final DeferredMoveset ODACHI_2H = MOVESETS.registerMoveset("odachi_2h", () ->
            Moveset.builder()
                    .addLivingMotionModifier(
                            LivingMotions.IDLE, OdachiAnimations.ODACHI_IDLE
                    )
                    .addLivingMotionModifier(
                            LivingMotions.WALK, OdachiAnimations.ODACHI_WALK
                    )
                    .addLivingMotionModifier(
                             LivingMotions.RUN, OdachiAnimations.ODACHI_RUN
                    )
                    .addLivingMotionModifier(
                             LivingMotions.KNEEL, OdachiAnimations.ODACHI_IDLE
                    )
                    .addLivingMotionModifier(
                             LivingMotions.SNEAK, OdachiAnimations.ODACHI_SNEAK
                    )
                    .addComboAttacks(
                            Animations.UCHIGATANA_AUTO1,
                            Animations.UCHIGATANA_AUTO2,
                            Animations.UCHIGATANA_AUTO3,
                            Animations.UCHIGATANA_DASH,
                            Animations.UCHIGATANA_AIR_SLASH
                    )
                    .addInnateSkill((itemStack, playerPatch) -> EpicFightSkills.GRASPING_SPIRE.get())
    );

    public static final DeferredMoveset ODACHI_1H = MOVESETS.registerMoveset("odachi_1h", () ->
            Moveset.builder()
                    .addLivingMotionModifier(
                            LivingMotions.IDLE, OdachiAnimations.ODACHI_IDLE_ONEHAND
                    )
                    .addLivingMotionModifier(
                            LivingMotions.WALK, OdachiAnimations.ODACHI_WALK_ONEHAND
                    )
                    .addLivingMotionModifier(
                            LivingMotions.RUN, OdachiAnimations.ODACHI_RUN
                    )
                    .addLivingMotionModifier(
                            LivingMotions.KNEEL, OdachiAnimations.ODACHI_IDLE_ONEHAND
                    )
                    .addLivingMotionModifier(
                            LivingMotions.SNEAK, OdachiAnimations.ODACHI_SNEAK
                    )
                    .addComboAttacks(
                            Animations.LONGSWORD_AUTO1,
                            Animations.LONGSWORD_AUTO2,
                            Animations.LONGSWORD_AUTO3,
                            Animations.LONGSWORD_DASH,
                            Animations.LONGSWORD_AIR_SLASH
                    )
                    .addInnateSkill((itemStack, playerPatch) -> EpicFightSkills.LIECHTENAUER.get())
    );
}
