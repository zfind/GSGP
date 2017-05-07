package hr.fer.zemris.seminar.rng;

import hr.fer.zemris.seminar.rng.rngimpl.RNGMersenneTwisterImpl;

/**
 * Created by zac on 14.02.17.
 */
public class RNG {

    private static IRNG random = new RNGMersenneTwisterImpl();

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static double nextDouble(double min, double max) {
        return random.nextDouble(min, max);
    }

    public static float nextFloat() {
        return random.nextFloat();
    }

    public static float nextFloat(float min, float max) {
        return random.nextFloat(min, max);
    }

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int min, int max) {
        return random.nextInt(min, max);
    }

    public static boolean nextBoolean() {
        return random.nextBoolean();
    }

    public static double nextGaussian() {
        return random.nextGaussian();
    }

}
