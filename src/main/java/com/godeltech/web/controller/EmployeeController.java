package com.godeltech.web.controller;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.AssignmentException;
import com.godeltech.mapper.EmployeeMapper;
import com.godeltech.persistence.model.Employee;
import com.godeltech.service.EmployeeService;
import com.godeltech.service.FlightCrewService;
import com.godeltech.web.dto.request.EmployeeRequestDto;
import com.godeltech.web.dto.response.EmployeeResponseDto;
import liquibase.pro.packaged.E;
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
@RequestMapping("/employees")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final FlightCrewService flightCrewService;
    private final LocalMessageSource localMessageSource;
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> findById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Find employee with id:{}", id);
        return new ResponseEntity<>(employeeMapper.toEmployeeResponseDto(employeeService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>> findAll() {
        log.info("Find all categories");
        return new ResponseEntity<>(employeeMapper.toEmployeeResponseDtoList(employeeService.findAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDto> save(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info("Save new employee");
        return new ResponseEntity<>((employeeMapper.toEmployeeResponseDto(employeeService.save(employeeMapper.toEmployee(employeeRequestDto)))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Delete employee with id:{}", id);
        final boolean isAssignedToFlight = flightCrewService.findAll().stream()
                .flatMap(flight -> flight.getEmployees().stream())
                .map(Employee::getId)
                .toList()
                .contains(id);
        if (isAssignedToFlight) {
            throw new AssignmentException(localMessageSource.getMessage("error.record.isAssignment", new Object[]{}));
        } else {
            employeeService.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeResponseDto> update(@PathVariable @Validated @NotNull @Positive Long id,
                                                      @RequestBody EmployeeRequestDto employeeRequestDto) {
        log.info("Update employee with id:{}", id);
        final Employee employee = employeeService.findById(id);
        employeeMapper.updateEntity(employee,employeeRequestDto);
        return new ResponseEntity<>(employeeMapper.toEmployeeResponseDto(employeeService.update(employee)), HttpStatus.OK);
    }
}
