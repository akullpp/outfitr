package de.akull.controller.exception;

import de.akull.client.WeatherClient;
import de.akull.controller.RecommendationController;
import de.akull.utility.Messages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import static org.hamcrest.Matchers.isA;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommendationController.class)
public class RuntimeExceptionHandlerTest {

    private static final String ENDPOINT = "/api/v1/recommendation";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherClient weatherClient;

    @MockBean
    private Messages messages;

    @Test
    public void Should_Respond_Only_With_An_UUID_If_An_Exception_Occured() throws Exception {
        when(weatherClient.getWeather(any(), any())).thenThrow(new NullPointerException());
        String path = UriComponentsBuilder.newInstance()
                .path(ENDPOINT)
                .queryParam("city", "foo")
                .queryParam("country", "bar")
                .buildAndExpand()
                .toUriString();

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(path));

        result.andExpect(status().is(500))
                .andExpect(jsonPath("$.uuid", isA(String.class)))
                .andExpect(jsonPath("$.message").doesNotExist());
    }
}
