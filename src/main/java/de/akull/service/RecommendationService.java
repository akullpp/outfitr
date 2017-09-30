package de.akull.service;

import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    private static final int[] BOUNDARIES = {5, 15, 21, 26};

    public static int createRecommendation(int degree) {
        return createRecommendation(degree, BOUNDARIES);
    }

    /*
     * Thoughts for discussion:
     *
     * The levels can't be expressed as an enum value since there's no inherent semantics attached to them, e.g. COLD,
     * COLDER, SLIGHTLY_LESS_COLDER, and maintenance would be an issue.
     *
     * A similar issue would occur if you'd use ifs to discriminate which would not only lead to cognitive complexity
     * but is a completely unsemantic way to express the nature of the issue, i.e. a scale with upper bound checks.
     *
     * Therefore I choose an algorithm for the checks whereas extensibility is provided by a plain array which reduces
     * maintenance complexity quite a bit.
     *
     * O(n)
     */
    public static int createRecommendation(int degree, int[] boundaries) {
        for (int i = 0; i < boundaries.length; i++) {
            if (degree <= boundaries[i]) {
                return boundaries.length + 1 - i;
            }
        }
        return 1;
    }
}
