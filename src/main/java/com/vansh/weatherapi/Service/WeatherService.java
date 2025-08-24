package com.vansh.weatherapi.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


    @Service
    public class WeatherService {

        private final WebClient webClient;

        @Value("${openweathermap.api.key}")
        private String apiKey;

        public WeatherService(WebClient.Builder builder) {
            this.webClient = builder.baseUrl("https://api.openweathermap.org/data/2.5").build();
        }

        public String getWeather(String city) {
            return webClient.get()
                    .uri("/weather?q={city}&appid={key}&units=metric", city, apiKey)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block(); // synchronous
        }
    }

