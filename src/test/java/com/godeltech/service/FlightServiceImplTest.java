package com.godeltech.service;

import com.godeltech.persistence.model.*;
import com.godeltech.persistence.reposirtory.FlightRepository;
import com.godeltech.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightServiceImplTest {
    private final static Long ID = 1L;
    private Flight expectedFlight;
    private List<Flight> expectedFlightList;
    @Mock
    private FlightRepository flightRepository;
    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void init() {
        Category category = Category.builder().id(ID).name("Passenger").build();
        Aircraft aircraft = new Aircraft(ID, "An-24", category);
        Set<Employee> employees = new HashSet<>(Set.of(Employee.builder().id(3L).build(),
                Employee.builder().id(4L).build()));
        FlightCrew flightCrew = new FlightCrew(ID, "name", employees);

        expectedFlight = new Flight(ID, aircraft, flightCrew, LocalDate.of(2023, 4, 16), LocalDate.of(2023, 4, 17));
        expectedFlightList = new ArrayList<>(List.of(Flight.builder()
                        .id(3L)
                        .departureDate(LocalDate.of(2023, 4, 16))
                        .arrivalDate(LocalDate.of(2023, 4, 17))
                        .build(),
                Flight.builder().id(4L)
                        .departureDate(LocalDate.of(2023, 4, 18))
                        .arrivalDate(LocalDate.of(2023, 4, 19))
                        .build()));
    }

    @Test
    void testFindById() {
        when(flightRepository.findById(ID)).thenReturn(Optional.of(expectedFlight));

        Flight actualAirPlane = flightService.findById(ID);

        verify(flightRepository, times(1)).findById(ID);

        Assertions.assertEquals(expectedFlight, actualAirPlane);
    }

    @Test
    void testFindAll() {
        when(flightRepository.findAll()).thenReturn(expectedFlightList);

        List<Flight> actualAirPlaneList = flightService.findAll();

        verify(flightRepository, times(1)).findAll();

        Assertions.assertEquals(expectedFlightList, actualAirPlaneList);
    }

    @Test
    void testSave() {
        when(flightRepository.save(expectedFlight)).thenReturn(expectedFlight);

        Flight actualFlight = flightService.save(expectedFlight);

        verify(flightRepository, times(1)).save(expectedFlight);

        Assertions.assertEquals(expectedFlight, actualFlight);
    }

    @Test
    void testDeleteById() {
        doReturn(Optional.of(expectedFlight)).when(flightRepository).findById(ID);

        flightService.deleteById(ID);

        verify(flightRepository, times(1)).deleteById(ID);

    }

    @Test
    void testUpdate() {
        doReturn(Optional.of(expectedFlight)).when(flightRepository).findById(ID);

        when(flightRepository.saveAndFlush(expectedFlight)).thenReturn(expectedFlight);

        Flight actualFlight = flightService.update(expectedFlight);

        verify(flightRepository, times(1)).saveAndFlush(expectedFlight);

        Assertions.assertEquals(expectedFlight, actualFlight);
    }
}
