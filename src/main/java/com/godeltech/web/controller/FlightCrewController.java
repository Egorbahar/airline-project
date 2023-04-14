package com.godeltech.web.controller;

import com.godeltech.service.FlightCrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight-crews")
@RequiredArgsConstructor
public class FlightCrewController {
    private final FlightCrewService flightCrewService;
}
