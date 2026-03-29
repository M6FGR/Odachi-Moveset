package pierceth.odm.api.math;


public class MathUtil {

    public static float limit(float val, float limit) {
        return Math.min(val, limit);
    }

    public static int toHex(float r, float g, float b) {
        int red = Math.round(r * 255.0F) << 16;
        int green = Math.round(g * 255.0F) << 8;
        int blue = Math.round(b * 255.0F);

        return red | green | blue;
    }

}
