package com.example.DiceAssignment.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {
    public ResponseEntity<String> RapidApiGetForecastSummaryByLocationName(String name);

    public ResponseEntity<String> RapidApiGetHourlyForecastByLocationName(String Name);
}