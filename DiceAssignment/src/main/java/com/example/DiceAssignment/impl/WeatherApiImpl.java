package com.example.DiceAssignment.impl;

import com.example.DiceAssignment.service.WeatherService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class WeatherApiImpl implements WeatherService {

    private static Logger logger = LoggerFactory.getLogger(WeatherApiImpl.class);

    @Value("${weather.HourlyURL}")
    private String HourlyURL ;

    @Value("${weather.Url}")
    private String Url;

    @Value("${weather.Key}")
    private String Key;

    @Value("${weather.host}")
    private String host;


    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ResponseEntity<String> RapidApiGetForecastSummaryByLocationName(String name) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Key", Key);
            httpHeaders.set("X-RapidAPI-Host", host);
            String updatedUrl = Url.replace("{name}", name);
            System.out.println("Using Concatenation "+updatedUrl);
            ResponseEntity<String> response = restTemplate.exchange(updatedUrl, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
            logger.info("Output from API is {} ",response);
            return response;
        }
        catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("The message is {}",ex.getMessage());
            throw new ResponseStatusException(ex.getStatusCode(),ex.getMessage());
        }
        catch (Exception e){
            logger.error("Something went wrong while getting respnse from Weather API");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error when calling the end point");
        }

    }

    @Override
    public ResponseEntity<String> RapidApiGetHourlyForecastByLocationName(String name) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-RapidAPI-Key", Key);
            httpHeaders.set("X-RapidAPI-Host", host);
            String updatedUrl1 = HourlyURL.replace("{name}", name);
            System.out.println("Using Concatenation "+updatedUrl1);
            ResponseEntity<String> response = restTemplate.exchange(updatedUrl1, HttpMethod.GET, new HttpEntity<>(httpHeaders), String.class);
            logger.info("Output from API is {} ",response);
            return response;
        }
        catch (HttpClientErrorException | HttpServerErrorException ex) {
            logger.error("The message is {}",ex.getMessage());
            throw new ResponseStatusException(ex.getStatusCode(),ex.getMessage());
        } catch (Exception e){
            logger.error("Something went wrong while getting respnse from Weather API");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error when calling the end point");
        }
    }
}