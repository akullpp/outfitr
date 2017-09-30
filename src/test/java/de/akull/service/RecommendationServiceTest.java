package de.akull.service;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
@RunWith(Parameterized.class)
public class RecommendationServiceTest {

    private final int degree;
    private final int expected;

    @Parameters
    public static Collection data() {
        return Arrays.asList(new Integer[][]{
                {Integer.MIN_VALUE, 5}, {5, 5},
                {6, 4}, {15, 4},
                {16, 3}, {21, 3},
                {22, 2}, {26, 2},
                {27, 1}, {Integer.MAX_VALUE, 1}
        });
    }

    @Test
    public void Should_Create_Valid_Recommendations() throws Exception {
        int[] boundaries = {5, 15, 21, 26};

        int result = RecommendationService.createRecommendation(degree, boundaries);

        assertThat(result).isEqualTo(expected);
    }
}
