package pierceth.odm.api.animation;

import com.mojang.serialization.Codec;
import yesman.epicfight.api.animation.property.AnimationProperty.StaticAnimationProperty;

import javax.annotation.Nullable;

public class SimpleAnimationProperty<T> extends StaticAnimationProperty<T> {

    public static final SimpleAnimationProperty<Float> PLAY_SPEED = new SimpleAnimationProperty<>("play_speed", Codec.FLOAT);


    public SimpleAnimationProperty(String rl, @Nullable Codec<T> codecs) {
        super(rl, codecs);
    }

    public SimpleAnimationProperty(String name) {
        this(name, null);
    }

    public SimpleAnimationProperty() {
        this(null, null);
    }
}
