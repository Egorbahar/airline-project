package com.godeltech.persistence.reposirtory;

import com.godeltech.persistence.model.FlightCrew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightCrewRepository extends JpaRepository<FlightCrew,Long> {
}
