package com.godeltech.web.controller;

import com.godeltech.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airplanes")
@RequiredArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;
}
