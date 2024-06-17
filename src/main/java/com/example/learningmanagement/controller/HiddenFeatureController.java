package com.example.learningmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/hidden-feature")
public class HiddenFeatureController {

    private static final String NUMBERS_API_URL = "http://numbersapi.com/";

    @GetMapping("/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        String apiUrl = NUMBERS_API_URL + number + "/trivia";
        RestTemplate restTemplate = new RestTemplate();
        String fact = restTemplate.getForObject(apiUrl, String.class);
        return new ResponseEntity<>(fact, HttpStatus.OK);
    }
}
