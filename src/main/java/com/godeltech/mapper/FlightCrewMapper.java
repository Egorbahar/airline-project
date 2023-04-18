package com.godeltech.mapper;

import com.godeltech.persistence.model.Employee;
import com.godeltech.persistence.model.FlightCrew;
import com.godeltech.web.dto.request.FlightCrewRequestDto;
import com.godeltech.web.dto.response.FlightCrewResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FlightCrewMapper {
    default FlightCrewResponseDto toFlightCrewResponseDto(FlightCrew flightCrew) {
        if (flightCrew.getEmployees() == null) {
            return new FlightCrewResponseDto(flightCrew.getId(), flightCrew.getName(), new HashMap<>());
        }
        Map<Long, String> employeeMap = flightCrew.getEmployees().stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getTitle));
        return new FlightCrewResponseDto(flightCrew.getId(), flightCrew.getName(), employeeMap);
    }

    FlightCrew toFlightCrew(FlightCrewRequestDto flightCrewRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget FlightCrew flightCrew, FlightCrewRequestDto flightCrewRequestDto);

    @IterableMapping(elementTargetType = FlightCrewResponseDto.class)
    List<FlightCrewResponseDto> toFlightCrewResponseDtoList(Collection<FlightCrew> flightCrews);
}
