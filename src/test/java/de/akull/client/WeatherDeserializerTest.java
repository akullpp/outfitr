package de.akull.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class WeatherDeserializerTest {

    private ObjectMapper mapper;

    @Before
    public void setupUp() {
        mapper = new ObjectMapper();
    }

    @After
    public void tearDown() {
        mapper = null;
    }

    @Test
    public void Should_Deserialize_OWM_Structured_JSON() throws Exception {
        String json = "{\"foo\": \"bar\",\"main\": {\"temp\": 1000}}";

        WeatherResponse result = mapper.readValue(json, WeatherResponse.class);

        assertThat(result).hasFieldOrPropertyWithValue("temperature", 1000);
    }
}
