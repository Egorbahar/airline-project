package com.godeltech.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "flight_crew")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @OneToMany(mappedBy = "flightCrew")
    private Set<Employee> employees = new LinkedHashSet<>();
}
