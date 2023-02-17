package com.example.personmicroservice.controller;

import com.example.personmicroservice.model.Person;
import com.example.personmicroservice.model.Weather;
import com.example.personmicroservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${location.url}")
    String url;

    @GetMapping("{id}/weather")
    public ResponseEntity<Weather> getWeather(@PathVariable int id){
        if(repository.existsById(id)){
            String location = repository.findById(id).get().getLocation();
            String locURl = String.format("http://%s/weather?location" , url );
            Weather weather = restTemplate.getForObject(locURl + location, Weather.class);
            return new ResponseEntity(weather,HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public Iterable<Person> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Person> findById(int id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Person> save(@RequestBody Person person) {
        return repository.findById(person.getId()).isPresent()
                ? new ResponseEntity(repository.findById(person.getId()), HttpStatus.BAD_REQUEST)
                : new ResponseEntity(repository.save(person), HttpStatus.CREATED);
    }
}
