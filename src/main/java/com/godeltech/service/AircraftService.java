package com.godeltech.service;

import com.godeltech.persistence.model.Aircraft;

import java.util.List;

public interface AircraftService {
    Aircraft findById(final Long id);

    List<Aircraft> findAll();

    Aircraft save(final Aircraft aircraft);

    Aircraft update(final Aircraft aircraft);

    void deleteById(final Long id);
}
