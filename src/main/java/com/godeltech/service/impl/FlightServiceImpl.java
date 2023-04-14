package com.godeltech.service.impl;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.ResourceNotFoundException;
import com.godeltech.persistence.model.Flight;
import com.godeltech.persistence.reposirtory.FlightRepository;
import com.godeltech.service.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final LocalMessageSource localMessageSource;

    @Override
    public Flight findById(final Long id) {
        log.debug("Find flight with id:{}", id);
        return flightRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(localMessageSource.getMessage("error.record.notExist", new Object[]{})));
    }

    @Override
    @Transactional
    public Flight save(final Flight flight) {
        log.debug("Save flight");
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> findAll() {
        log.debug("Find all flights");
        return flightRepository.findAll();
    }

    @Override
    @Transactional
    public Flight update(final Flight flight) {
        log.debug("Update flight with id:{}", flight.getId());
        findById(flight.getId());
        return flightRepository.saveAndFlush(flight);
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        log.debug("Delete flight with id:{}", id);
        findById(id);
        flightRepository.deleteById(id);
    }
}
