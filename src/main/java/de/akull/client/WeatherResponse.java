package de.akull.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

/**
 * OpenWeatherMap response.
 * <p>
 * Uses a custom deserializer instead of a nested object structure.
 */
@Data
@Builder
@JsonDeserialize(using = WeatherDeserializer.class)
public class WeatherResponse {

    private Integer temperature;
}
