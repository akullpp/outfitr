package de.akull.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * HTTP Client for OpenWeatherMap.
 * <p>
 * Feign is a declarative HTTP client by Netflix. The actual implementation will be provisioned at runtime.
 * <p>
 * Also it has build-in capability of using a fallback (Hystrix), client-side load-balancing (Ribbon) and service
 * discovery (Eureka).
 */
@FeignClient(name = "weather", url = "${owm.uri}")
public interface WeatherClient {

    /**
     * Uses the specific query language as described by http://openweathermap.com/current.
     * <p>
     * The API key should be provided as an environment variable.
     */
    @GetMapping("weather?q={query}&units={unit}&APPID=${OMW_API_KEY}")
    WeatherResponse getWeather(@RequestParam("query") String query, @RequestParam("unit") String unit);
}
