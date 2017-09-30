package de.akull.client;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class WeatherDeserializer extends JsonDeserializer {

    @Override
    public WeatherResponse deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        JsonNode tree = parser.readValueAsTree();
        return WeatherResponse.builder()
                .temperature(tree.get("main").get("temp").asInt())
                .build();
    }
}
