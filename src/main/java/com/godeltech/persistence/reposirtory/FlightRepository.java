package com.godeltech.persistence.reposirtory;

import com.godeltech.persistence.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
