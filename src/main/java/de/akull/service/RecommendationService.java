package de.akull.service;

public class RecommendationService {

    private static final int[] BOUNDARIES = {5, 15, 21, 26};

    public static int createRecommendation(int degree) {
        return createRecommendation(degree, BOUNDARIES);
    }

    public static int createRecommendation(int degree, int[] boundaries) {
        for (int i = 0; i < boundaries.length; i++) {
            if (degree <= boundaries[i]) {
                return boundaries.length + 1 - i;
            }
        }
        return 1;
    }
}
