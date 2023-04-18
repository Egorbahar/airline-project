package com.godeltech.service;

import com.godeltech.persistence.model.Employee;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.persistence.reposirtory.EmployeeRepository;
import com.godeltech.service.impl.EmployeeServiceImpl;
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
public class EmployeeServiceImplTest {
    private final static Long ID = 1L;
    private Employee expectedEmployee;
    private List<Employee> expectedEmployeeList;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void init() {
        FlightCrew flightCrew = FlightCrew.builder().id(ID).name("Name of crew").build();
        expectedEmployee = new Employee(ID, "John", "Wick", "pilot", flightCrew);//Employee.builder().id(ID).name("Ан-24").category(category).build();
        expectedEmployeeList = new ArrayList<>(List.of(Employee.builder().id(3L).flightCrew(flightCrew).build(),
                Employee.builder().id(4L).flightCrew(flightCrew).build()));
    }

    @Test
    void testFindById() {
        when(employeeRepository.findById(ID)).thenReturn(Optional.of(expectedEmployee));

        Employee actualAirPlane = employeeService.findById(ID);

        verify(employeeRepository, times(1)).findById(ID);

        assertEquals(expectedEmployee, actualAirPlane);
    }

    @Test
    void testFindAll() {
        when(employeeRepository.findAll()).thenReturn(expectedEmployeeList);

        List<Employee> actualAirPlaneList = employeeService.findAll();

        verify(employeeRepository, times(1)).findAll();

        assertEquals(expectedEmployeeList, actualAirPlaneList);
    }

    @Test
    void testSave() {
        when(employeeRepository.save(expectedEmployee)).thenReturn(expectedEmployee);

        Employee actualEmployee = employeeService.save(expectedEmployee);

        verify(employeeRepository, times(1)).save(expectedEmployee);

        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void testDeleteById() {
        doReturn(Optional.of(expectedEmployee)).when(employeeRepository).findById(ID);

        employeeService.deleteById(ID);

        verify(employeeRepository, times(1)).deleteById(ID);

    }

    @Test
    void testUpdate() {
        doReturn(Optional.of(expectedEmployee)).when(employeeRepository).findById(ID);

        when(employeeRepository.saveAndFlush(expectedEmployee)).thenReturn(expectedEmployee);

        Employee actualEmployee = employeeService.update(expectedEmployee);

        verify(employeeRepository, times(1)).saveAndFlush(expectedEmployee);

        assertEquals(expectedEmployee, actualEmployee);
    }
}
