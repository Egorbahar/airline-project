package com.godeltech.web.controller;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.AssignmentException;
import com.godeltech.mapper.FlightMapper;
import com.godeltech.persistence.model.Aircraft;
import com.godeltech.persistence.model.Flight;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.service.AircraftService;
import com.godeltech.service.FlightCrewService;
import com.godeltech.service.FlightService;
import com.godeltech.service.FlightService;
import com.godeltech.web.dto.request.FlightRequestDto;
import com.godeltech.web.dto.response.FlightResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
@Slf4j
public class FlightController {
    private final FlightService flightService;
    private final FlightMapper flightMapper;
    private final AircraftService aircraftService;
    private final FlightCrewService flightCrewService;

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> findById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Find flight with id:{}", id);
        return new ResponseEntity<>(flightMapper.toFlightResponseDto(flightService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> findAll() {
        log.info("Find all categories");
        return new ResponseEntity<>(flightMapper.toFlightResponseDtoList(flightService.findAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightResponseDto> save(@Valid @RequestBody FlightRequestDto flightRequestDto) {
        log.info("Save new flight");
        return new ResponseEntity<>((flightMapper.toFlightResponseDto(flightService.save(flightMapper.toFlight(flightRequestDto)))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Delete flight with id:{}", id);
        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<FlightResponseDto> update(@PathVariable @Validated @NotNull @Positive Long id,
                                                    @RequestBody FlightRequestDto flightRequestDto) {
        log.info("Update flight with id:{}", id);
        final Flight flight = flightService.findById(id);
        final Aircraft aircraft = aircraftService.findById(flightRequestDto.getAircraftId());
        final FlightCrew flightCrew = flightCrewService.findById(flightRequestDto.getFlightCrewId());
        flightMapper.updateEntity(flight, aircraft, flightRequestDto, flightCrew);
        return new ResponseEntity<>(flightMapper.toFlightResponseDto(flightService.update(flight)), HttpStatus.OK);
    }
}
