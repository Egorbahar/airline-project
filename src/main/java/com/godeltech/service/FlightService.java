package com.godeltech.service;

import com.godeltech.persistence.model.Flight;

import java.util.List;

public interface FlightService {
    Flight findById(final Long id);

    Flight save(final Flight flight);

    List<Flight> findAll();

    Flight update(final Flight flight);

    void deleteById(final Long id);
}
