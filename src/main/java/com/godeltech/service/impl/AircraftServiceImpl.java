package com.godeltech.service.impl;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.ResourceNotFoundException;
import com.godeltech.persistence.model.Aircraft;
import com.godeltech.persistence.reposirtory.AircraftRepository;
import com.godeltech.service.AircraftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AircraftServiceImpl implements AircraftService {
    private final AircraftRepository aircraftRepository;
    private final LocalMessageSource localMessageSource;
    @Override
    public Aircraft findById(final Long id) {
        log.debug("Find aircraft with id:{}", id);
        return aircraftRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(localMessageSource.getMessage("error.record.notExist", new Object[]{})));
    }

    @Override
    public List<Aircraft> findAll() {
        log.debug("Find all aircraft");
        return aircraftRepository.findAll();
    }

    @Override
    @Transactional
    public Aircraft save(final Aircraft aircraft) {
        log.debug("Save new aircraft");
        return aircraftRepository.save(aircraft);
    }

    @Override
    @Transactional
    public Aircraft update(final Aircraft aircraft) {
        log.debug("Update aircraft with id:{}", aircraft.getId());
        findById(aircraft.getId());
        return aircraftRepository.saveAndFlush(aircraft);
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        log.debug("Delete aircraft with id:{}", id);
        findById(id);
        aircraftRepository.deleteById(id);
    }
}
