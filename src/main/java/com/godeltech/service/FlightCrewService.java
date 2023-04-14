package com.godeltech.service;

import com.godeltech.persistence.model.Employee;
import com.godeltech.persistence.model.FlightCrew;

import java.util.List;

public interface FlightCrewService {
    FlightCrew findById(final Long id);

    FlightCrew save(final FlightCrew flightCrew);

    List<FlightCrew> findAll();

    FlightCrew update(final FlightCrew flightCrew);

    void deleteById(final Long id);
}
