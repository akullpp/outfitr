package de.akull.controller;

import de.akull.client.WeatherClient;
import de.akull.client.WeatherResponse;
import de.akull.domain.Recommendation;
import de.akull.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.stream.Stream;

import static de.akull.domain.Scale.CELSIUS;
import static java.util.stream.Collectors.joining;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Controller for outfit recommendations based on OpenWeatherMap.
 * <p>
 * Caching is done for every request by Ehcache.
 * Documentation is provided by Swagger.
 */
@RestController
@SuppressWarnings({"WeakerAccess", "unused"})
@RequestMapping("api/v1/recommendation")
public class RecommendationController {

    private WeatherClient weatherClient;

    @Autowired
    public RecommendationController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    /*
     * Thoughts for discussion:
     *
     * Searching via query parameters could lead to a multitude of parameters and thus complexity. There are several
     * options to resolve this issue:
     *
     * 1. Use Spring's automatic Map<String, String> deserialization feature which is not compliant with the Open API
     * specification and thus isn't supported by Swagger.
     * 2. Use a custom object which entails other decisions, e.g. how to ensure that the HATEOAS self link is valid,
     * e.g. via reflection.
     * 3. Use a POST since a search is created, persistence is not a necessity, which results several practical issues,
     * e.g. cacheability and bookmarking.
     *
     */
    @GetMapping
    @Cacheable("recommendations")
    public Resource<Recommendation> getRecommendation(
            @RequestParam String city,
            @RequestParam(required = false) String country
    ) {
        WeatherResponse weatherResponse = weatherClient.getWeather(
                Stream.of(city, country)
                        .filter(Objects::nonNull)
                        .collect(joining(",")),
                CELSIUS.getUnit()
        );
        return new Resource<>(
                Recommendation.builder()
                        .temperature(weatherResponse.getTemperature())
                        .level(RecommendationService.createRecommendation(weatherResponse.getTemperature()))
                        .scale(CELSIUS)
                        .build(),
                linkTo(methodOn(RecommendationController.class)
                        .getRecommendation(city, country))
                        .withSelfRel()
                        .expand()
        );
    }
}
