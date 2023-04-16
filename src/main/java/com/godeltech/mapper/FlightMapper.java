package com.godeltech.mapper;

import com.godeltech.persistence.model.Aircraft;
import com.godeltech.persistence.model.Flight;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.web.dto.request.FlightRequestDto;
import com.godeltech.web.dto.response.FlightResponseDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aircraft.id", source = "aircraftId")
    @Mapping(target = "flightCrew.id", source = "flightCrewId")
    @Mapping(target = "departureDate", source = "departureDate")
    @Mapping(target = "arrivalDate", source = "arrivalDate")
    Flight toFlight(FlightRequestDto flightRequestDto);

    default void updateEntity(final Flight flight,
                              final Aircraft aircraft,
                              final FlightRequestDto flightRequestDto,
                              final FlightCrew flightCrew) {
        flight.setAircraft(aircraft);
        flight.setDepartureDate(LocalDate.parse(flightRequestDto.getDepartureDate()));
        flight.setArrivalDate(LocalDate.parse(flightRequestDto.getArrivalDate()));
        flight.setFlightCrew(flightCrew);

    }
    @IterableMapping(elementTargetType = FlightResponseDto.class)
    List<FlightResponseDto> toFlightResponseDtoList(Collection<Flight> flights);
}
