package com.godeltech.mapper;

import com.godeltech.exception.DateTimeMismatchException;
import com.godeltech.persistence.model.Aircraft;
import com.godeltech.persistence.model.Flight;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.web.dto.request.FlightRequestDto;
import com.godeltech.web.dto.response.FlightResponseDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "departureDate", source = "departureDate")
    @Mapping(target = "arrivalDate", source = "arrivalDate")
    @Mapping(target = "aircraftId", source = "aircraft.id")
    @Mapping(target = "flightCrewId", source = "flightCrew.id")
    FlightResponseDto toFlightResponseDto(Flight flight);

    default Flight toFlight(FlightRequestDto flightRequestDto) {
        LocalDateTime departureDateTime = LocalDateTime.parse(flightRequestDto.getDepartureDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime arrivalDateTime = LocalDateTime.parse(flightRequestDto.getArrivalDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if (arrivalDateTime.isEqual(departureDateTime) || arrivalDateTime.isBefore(departureDateTime)) {
            throw new DateTimeMismatchException("Dates entered incorrectly!");
        }
        return Flight.builder()
                .aircraft(Aircraft.builder().id(flightRequestDto.getAircraftId()).build())
                .flightCrew(FlightCrew.builder().id(flightRequestDto.getFlightCrewId()).build())
                .arrivalDate(arrivalDateTime)
                .departureDate(departureDateTime)
                .build();
    }

    default void updateEntity(final Flight flight,
                              final Aircraft aircraft,
                              final FlightRequestDto flightRequestDto,
                              final FlightCrew flightCrew) {
        flight.setAircraft(aircraft);
        flight.setDepartureDate(LocalDateTime.parse(flightRequestDto.getDepartureDate()));
        flight.setArrivalDate(LocalDateTime.parse(flightRequestDto.getArrivalDate()));
        flight.setFlightCrew(flightCrew);

    }

    @IterableMapping(elementTargetType = FlightResponseDto.class)
    List<FlightResponseDto> toFlightResponseDtoList(Collection<Flight> flights);
}
