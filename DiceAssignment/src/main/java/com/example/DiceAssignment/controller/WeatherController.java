package com.example.DiceAssignment.controller;

import com.example.DiceAssignment.impl.WeatherApiImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
public class WeatherController {
    private final WeatherApiImpl weatherAPIimpl;

    public WeatherController(WeatherApiImpl weatherAPIimpl) {
        this.weatherAPIimpl = weatherAPIimpl;
    }

    // End Point for (RapidApiGetForecastSummaryByLocationName)
    @GetMapping("/{name}")
    public ResponseEntity<?> getSummaryByLocation(@PathVariable("name") String name){
        return ResponseEntity.ok(weatherAPIimpl.RapidApiGetForecastSummaryByLocationName(name));
    }

    // End point for (RapidApiGetHourlyForecastByLocationName)
    @GetMapping("/hourly/{name}")
    public ResponseEntity<?> getHourlyReportByLocation(@PathVariable("name") String name){
        return ResponseEntity.ok(weatherAPIimpl.RapidApiGetHourlyForecastByLocationName(name));
    }
}