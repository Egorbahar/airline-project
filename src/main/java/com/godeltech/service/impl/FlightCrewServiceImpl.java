package com.godeltech.service.impl;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.ResourceNotFoundException;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.persistence.reposirtory.FlightCrewRepository;
import com.godeltech.service.FlightCrewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class FlightCrewServiceImpl implements FlightCrewService {
    private final FlightCrewRepository flightCrewRepository;
    private final LocalMessageSource localMessageSource;

    @Override
    public FlightCrew findById(Long id) {
        log.debug("Find flight crew with id:{}", id);
        return flightCrewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(localMessageSource.getMessage("error.record.notExist", new Object[]{})));
    }

    @Override
    @Transactional
    public FlightCrew save(FlightCrew flightCrew) {
        log.debug("Save flight crew");
        return flightCrewRepository.save(flightCrew);
    }

    @Override
    public List<FlightCrew> findAll() {
        log.debug("Find all flight crews");
        return flightCrewRepository.findAll();
    }

    @Override
    @Transactional
    public FlightCrew update(FlightCrew flightCrew) {
        log.debug("Update flight crew with id:{}", flightCrew.getId());
        findById(flightCrew.getId());
        return flightCrewRepository.saveAndFlush(flightCrew);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("Delete flight crew with id:{}", id);
        findById(id);
        flightCrewRepository.deleteById(id);
    }
}
