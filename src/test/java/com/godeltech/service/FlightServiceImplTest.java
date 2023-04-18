package com.godeltech.service;

import com.godeltech.exception.AbsenceAircraftException;
import com.godeltech.persistence.model.*;
import com.godeltech.persistence.reposirtory.FlightRepository;
import com.godeltech.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        expectedFlight = new Flight(ID, aircraft, flightCrew, LocalDateTime.of(2023, 4, 16, 12, 40), LocalDateTime.of(2023, 4, 16, 22, 40));
        expectedFlightList = new ArrayList<>(List.of(Flight.builder()
                        .id(3L)
                        .departureDate(LocalDateTime.of(2023, 4, 16, 12, 40))
                        .arrivalDate(LocalDateTime.of(2023, 4, 16, 22, 40))
                        .build(),
                Flight.builder().id(4L)
                        .departureDate(LocalDateTime.of(2023, 4, 18, 12, 40))
                        .arrivalDate(LocalDateTime.of(2023, 4, 19, 12, 40))
                        .build()));
    }

    @Test
    void testFindById() {
        when(flightRepository.findById(ID)).thenReturn(Optional.of(expectedFlight));

        Flight actualAirPlane = flightService.findById(ID);

        verify(flightRepository, times(1)).findById(ID);

        assertEquals(expectedFlight, actualAirPlane);
    }

    @Test
    void testFindAll() {
        when(flightRepository.findAll()).thenReturn(expectedFlightList);

        List<Flight> actualAirPlaneList = flightService.findAll();

        verify(flightRepository, times(1)).findAll();

        assertEquals(expectedFlightList, actualAirPlaneList);
    }

    @Test
    void testSave() {
        when(flightRepository.save(expectedFlight)).thenReturn(expectedFlight);

        Flight actualFlight = flightService.save(expectedFlight);

        verify(flightRepository, times(1)).save(expectedFlight);

        assertEquals(expectedFlight, actualFlight);
    }

    @Test
    void testSaveWithAbsenceOfAircraft() throws Exception {
        when(invokePrivateMethod(flightService, "checkAbsenceOfFlightCrew", expectedFlight))
                .thenThrow(new AbsenceAircraftException("This aircraft is assignment for another flight!"));
        assertThrows(AbsenceAircraftException.class, () -> flightService.save(expectedFlight), "This aircraft is assignment for another flight!");

    }

    @Test
    void testSaveWithAbsenceOfFlightCrew() throws Exception {
        when(invokePrivateMethod(flightService, "checkAbsenceOfAircraft", expectedFlight))
                .thenThrow(new AbsenceAircraftException("This flight crew is assignment another flight!"));
        assertThrows(AbsenceAircraftException.class, () -> flightService.save(expectedFlight), "This flight crew is assignment another flight!");
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

        assertEquals(expectedFlight, actualFlight);
    }

    private Object invokePrivateMethod(Object test, String methodName, Object param) throws Exception {
        Object ret = null;

        final Method[] methods =
                test.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                method.setAccessible(true);
                ret = method.invoke(test, param);
                break;
            }
        }
        return ret;
    }
}
