package com.godeltech.web.controller;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.AssignmentException;
import com.godeltech.mapper.AircraftMapper;
import com.godeltech.persistence.model.Aircraft;
import com.godeltech.persistence.model.Category;
import com.godeltech.service.AircraftService;
import com.godeltech.service.CategoryService;
import com.godeltech.service.FlightService;
import com.godeltech.web.dto.request.AircraftRequestDto;
import com.godeltech.web.dto.response.AircraftResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;
@Validated
@RestController
@RequestMapping("/aircraft")
@RequiredArgsConstructor
@Slf4j
public class AircraftController {
    private final AircraftService aircraftService;
    private final CategoryService categoryService;
    private final AircraftMapper aircraftMapper;
    private final FlightService flightService;
    private final LocalMessageSource localMessageSource;

    @GetMapping("/{id}")
    public ResponseEntity<AircraftResponseDto> findById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Find aircraft with id:{}", id);
        return new ResponseEntity<>(aircraftMapper.toAircraftResponseDto(aircraftService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponseDto>> findAll() {
        log.info("Find all aircraft");
        return new ResponseEntity<>(aircraftMapper.toAircraftResponseDtoList(aircraftService.findAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AircraftResponseDto> save(@Validated @RequestBody AircraftRequestDto aircraftRequestDto) {
        log.info("Save new aircraft");
        return new ResponseEntity<>(aircraftMapper.toAircraftResponseDto(aircraftService.save(aircraftMapper.toAircraft(aircraftRequestDto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Delete aircraft with id:{}", id);
        final boolean isAssignedToFlight = flightService.findAll().stream()
                .map(flight -> flight.getAircraft().getId())
                .toList()
                .contains(id);
        if (isAssignedToFlight) {
            throw new AssignmentException(localMessageSource.getMessage("error.record.isAssignment", new Object[]{}));
        } else {
            aircraftService.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<AircraftResponseDto> update(@PathVariable @Validated @NotNull @Positive Long id,
                                                      @RequestBody AircraftRequestDto aircraftRequestDto) {
        log.info("Update aircraft with id:{}", id);
        final Aircraft aircraft = aircraftService.findById(id);
        final Category category = categoryService.findById(aircraftRequestDto.getCategoryId());
        aircraftMapper.updateEntity(aircraft, aircraftRequestDto, category);
        return new ResponseEntity<>(aircraftMapper.toAircraftResponseDto(aircraftService.update(aircraft)), HttpStatus.OK);
    }
}
