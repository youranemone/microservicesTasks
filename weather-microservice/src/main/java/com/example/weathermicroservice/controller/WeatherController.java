package com.example.weathermicroservice.controller;

import com.example.weathermicroservice.model.Main;
import com.example.weathermicroservice.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@RestController
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${appid}")
    private String appId;
    @Value("${url.weather}")
    private String urlWeather;

    @GetMapping("/")
    public ResponseEntity<Main> getWeather(@RequestParam String lat, @RequestParam String lon){
        String request = String.format("%s?lat=%s&lon=%s&units=metric&appid=%s", urlWeather, lat, lon, appId);
        CacheControl control = CacheControl.maxAge(1, TimeUnit.MINUTES);
        return ResponseEntity.ok().cacheControl(control).body(restTemplate.getForObject(request,Root.class).getMain());
    }
}
