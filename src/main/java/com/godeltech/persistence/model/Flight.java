package com.godeltech.persistence.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "flight")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aircraft_id", nullable = false)
    private Aircraft aircraft;
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "flight_crew_id", nullable = false)
    private FlightCrew flightCrew;
    @Column(name = "departure_date")
    private LocalDate departureDate;
    @Column(name = "arrival_date")
    private LocalDate arrivalDate;
}
