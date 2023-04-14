package com.godeltech.persistence.reposirtory;

import com.godeltech.persistence.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
}
