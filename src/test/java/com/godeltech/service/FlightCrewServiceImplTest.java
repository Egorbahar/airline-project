package com.godeltech.service;

import com.godeltech.persistence.model.Employee;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.persistence.reposirtory.FlightCrewRepository;
import com.godeltech.service.impl.FlightCrewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FlightCrewServiceImplTest {
    private final static Long ID = 1L;
    private FlightCrew expectedFlightCrew;
    private List<FlightCrew> expectedFlightCrewList;
    @Mock
    private FlightCrewRepository flightCrewRepository;
    @InjectMocks
    private FlightCrewServiceImpl flightCrewService;

    @BeforeEach
    void init() {
        Set<Employee> employees = new HashSet<>(Set.of(Employee.builder().id(3L).build(),
                Employee.builder().id(4L).build()));
        expectedFlightCrew = new FlightCrew(ID, "name", employees);
        expectedFlightCrewList = new ArrayList<>(List.of(FlightCrew.builder().id(3L).employees(employees).build(),
                FlightCrew.builder().id(4L).employees(employees).build()));
    }

    @Test
    void testFindById() {
        when(flightCrewRepository.findById(ID)).thenReturn(Optional.of(expectedFlightCrew));

        FlightCrew actualAirPlane = flightCrewService.findById(ID);

        verify(flightCrewRepository, times(1)).findById(ID);

        assertEquals(expectedFlightCrew, actualAirPlane);
    }

    @Test
    void testFindAll() {
        when(flightCrewRepository.findAll()).thenReturn(expectedFlightCrewList);

        List<FlightCrew> actualAirPlaneList = flightCrewService.findAll();

        verify(flightCrewRepository, times(1)).findAll();

        assertEquals(expectedFlightCrewList, actualAirPlaneList);
    }

    @Test
    void testSave() {
        when(flightCrewRepository.save(expectedFlightCrew)).thenReturn(expectedFlightCrew);

        FlightCrew actualFlightCrew = flightCrewService.save(expectedFlightCrew);

        verify(flightCrewRepository, times(1)).save(expectedFlightCrew);

        assertEquals(expectedFlightCrew, actualFlightCrew);
    }

    @Test
    void testDeleteById() {
        doReturn(Optional.of(expectedFlightCrew)).when(flightCrewRepository).findById(ID);

        flightCrewService.deleteById(ID);

        verify(flightCrewRepository, times(1)).deleteById(ID);

    }

    @Test
    void testUpdate() {
        doReturn(Optional.of(expectedFlightCrew)).when(flightCrewRepository).findById(ID);

        when(flightCrewRepository.saveAndFlush(expectedFlightCrew)).thenReturn(expectedFlightCrew);

        FlightCrew actualFlightCrew = flightCrewService.update(expectedFlightCrew);

        verify(flightCrewRepository, times(1)).saveAndFlush(expectedFlightCrew);

        assertEquals(expectedFlightCrew, actualFlightCrew);
    }
}
