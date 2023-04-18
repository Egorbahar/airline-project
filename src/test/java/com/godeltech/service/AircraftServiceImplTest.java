package com.godeltech.service;

import com.godeltech.persistence.model.Aircraft;
import com.godeltech.persistence.model.Category;
import com.godeltech.persistence.reposirtory.AircraftRepository;
import com.godeltech.service.impl.AircraftServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftServiceImplTest {
    private final static Long ID = 1L;
    private Aircraft expectedAircraft;
    private List<Aircraft> expectedAircraftList;
    @Mock
    private AircraftRepository aircraftRepository;
    @InjectMocks
    private AircraftServiceImpl aircraftService;

    @BeforeEach
    void init() {
        Category category = Category.builder().id(ID).name("Transport").build();
        expectedAircraft = new Aircraft(ID,"An-24",category);
        expectedAircraftList = new ArrayList<>(List.of(Aircraft.builder().id(3L).category(category).build(),
                Aircraft.builder().id(4L).category(category).build()));
    }

    @Test
    void testFindById() {
        when(aircraftRepository.findById(ID)).thenReturn(Optional.of(expectedAircraft));

        Aircraft actualAirPlane = aircraftService.findById(ID);

        verify(aircraftRepository, times(1)).findById(ID);

        assertEquals(expectedAircraft, actualAirPlane);
    }

    @Test
    void testFindAll() {
        when(aircraftRepository.findAll()).thenReturn(expectedAircraftList);

        List<Aircraft> actualAirPlaneList = aircraftService.findAll();

        verify(aircraftRepository, times(1)).findAll();

        assertEquals(expectedAircraftList, actualAirPlaneList);
    }

    @Test
    void testSave() {
        when(aircraftRepository.save(expectedAircraft)).thenReturn(expectedAircraft);

        Aircraft actualAircraft = aircraftService.save(expectedAircraft);

        verify(aircraftRepository, times(1)).save(expectedAircraft);

        assertEquals(expectedAircraft, actualAircraft);
    }

    @Test
    void testDeleteById() {
        doReturn(Optional.of(expectedAircraft)).when(aircraftRepository).findById(ID);

        aircraftService.deleteById(ID);

        verify(aircraftRepository, times(1)).deleteById(ID);

    }

    @Test
    void testUpdate() {
        doReturn(Optional.of(expectedAircraft)).when(aircraftRepository).findById(ID);

        when(aircraftRepository.saveAndFlush(expectedAircraft)).thenReturn(expectedAircraft);

        Aircraft actualAircraft = aircraftService.update(expectedAircraft);

        verify(aircraftRepository, times(1)).saveAndFlush(expectedAircraft);

        assertEquals(expectedAircraft, actualAircraft);
    }
}