package de.akull.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommendationController.class)
public class RecommendationControllerTest {

    private static final String ENDPOINT = "/api/v1/recommendation";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Should_Respond_With_A_Recommendation() throws Exception {
        String path = UriComponentsBuilder.newInstance()
                .path(ENDPOINT)
                .queryParam("city", "foo")
                .queryParam("country", "bar")
                .buildAndExpand()
                .toUriString();

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(path));

        result.andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.scale").exists())
            .andExpect(jsonPath("$._links.self.href", endsWith(path)));
    }
}
