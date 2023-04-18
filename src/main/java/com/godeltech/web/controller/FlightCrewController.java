package com.godeltech.web.controller;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.AssignmentException;
import com.godeltech.mapper.FlightCrewMapper;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.service.FlightCrewService;
import com.godeltech.service.FlightService;
import com.godeltech.web.dto.request.FlightCrewRequestDto;
import com.godeltech.web.dto.response.FlightCrewResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
@RestController
@RequestMapping("/flight-crews")
@RequiredArgsConstructor
@Slf4j
public class FlightCrewController {
    private final FlightCrewService flightCrewService;
    private final FlightCrewMapper flightCrewMapper;
    private final FlightService flightService;
    private final LocalMessageSource localMessageSource;

    @GetMapping("/{id}")
    public ResponseEntity<FlightCrewResponseDto> findById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Find flightCrew with id:{}", id);
        return new ResponseEntity<>(flightCrewMapper.toFlightCrewResponseDto(flightCrewService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FlightCrewResponseDto>> findAll() {
        log.info("Find all flightCrew");
        return new ResponseEntity<>(flightCrewMapper.toFlightCrewResponseDtoList(flightCrewService.findAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightCrewResponseDto> save(@Validated @RequestBody FlightCrewRequestDto flightCrewRequestDto) {
        log.info("Save new flightCrew");
        return new ResponseEntity<>(flightCrewMapper.toFlightCrewResponseDto(flightCrewService.save(flightCrewMapper.toFlightCrew(flightCrewRequestDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Delete flightCrew with id:{}", id);
        final boolean isAssignedToFlight = flightService.findAll().stream()
                .map(flight -> flight.getFlightCrew().getId())
                .toList()
                .contains(id);
        if (isAssignedToFlight) {
            throw new AssignmentException(localMessageSource.getMessage("error.record.isAssignment", new Object[]{}));
        } else {
            flightCrewService.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<FlightCrewResponseDto> update(@PathVariable @Validated @NotNull @Positive Long id,
                                                        @RequestBody FlightCrewRequestDto flightCrewRequestDto) {
        log.info("Update flightCrew with id:{}", id);
        final FlightCrew flightCrew = flightCrewService.findById(id);
        flightCrewMapper.updateEntity(flightCrew, flightCrewRequestDto);
        return new ResponseEntity<>(flightCrewMapper.toFlightCrewResponseDto(flightCrewService.update(flightCrew)), HttpStatus.OK);
    }
}
