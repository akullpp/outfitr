package de.akull.client;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(using = WeatherDeserializer.class)
public class WeatherResponse {

    private Integer temperature;
}
