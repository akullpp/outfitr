package de.akull.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather", url = "${owm.uri}")
public interface WeatherClient {

    @GetMapping("weather?q={query}&units={unit}&APPID=${OMW_API_KEY}")
    WeatherResponse getWeather(@RequestParam("query") String query, @RequestParam("unit") String unit);
}
