package com.example.locationmicroservice.controller;

import com.example.locationmicroservice.model.Geodata;
import com.example.locationmicroservice.model.Weather;
import com.example.locationmicroservice.repository.GeodataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class GeodataController {
    @Autowired
    GeodataRepository geodataRepository;
    @Autowired
    RestTemplate restTemplate;
    @Value("${weather.url}")
    String weatherUrl;

    @GetMapping("/weather")
    public ResponseEntity<Weather> redirectRequestWeather(@RequestParam String location) {
        Geodata geodata = geodataRepository.findByName(location).orElse(null);
        if(geodata == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        String url = String.format("http://%s/?lat=%s&lon=%s", weatherUrl, geodata.getLat(), geodata.getLon());
        return new ResponseEntity<Weather>(restTemplate.getForObject(url, Weather.class), HttpStatus.OK);
    }

    @GetMapping
    public Optional<Geodata> getWeather(@RequestParam String location) {
        return geodataRepository.findByName(location);
    }

    @PostMapping("/")
    public Geodata save(@RequestBody Geodata geodata) {
        return geodataRepository.save(geodata);
    }
}
