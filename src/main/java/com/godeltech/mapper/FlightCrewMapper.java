package com.godeltech.mapper;

import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.web.dto.request.FlightCrewRequestDto;
import com.godeltech.web.dto.response.FlightCrewResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightCrewMapper {
    FlightCrewResponseDto toFlightCrewResponseDto(FlightCrew flightCrew);

    @Mapping(target = "id", ignore = true)
    FlightCrew toFlightCrew(FlightCrewRequestDto flightCrewRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget FlightCrew flightCrew, FlightCrewRequestDto flightCrewRequestDto);

    @IterableMapping(elementTargetType = FlightCrewResponseDto.class)
    List<FlightCrewResponseDto> toFlightCrewResponseDtoList(Collection<FlightCrew> flightCrews);
}
