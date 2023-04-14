package com.godeltech.mapper;

import com.godeltech.persistence.model.Aircraft;
import com.godeltech.persistence.model.Category;
import com.godeltech.web.dto.request.AircraftRequestDto;
import com.godeltech.web.dto.response.AircraftResponseDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
    AircraftMapper INSTANCE = Mappers.getMapper(AircraftMapper.class);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "categoryName", source = "category.name")
    AircraftResponseDto toAircraftResponseDto(Aircraft aircraft);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "windSpeed" , source = "windSpeed")
    Aircraft toAircraft(AircraftRequestDto aircraftRequestDto);

    default void updateEntity(Aircraft aircraft, AircraftRequestDto aircraftRequestDto, Category category) {
        aircraft.setName(aircraftRequestDto.getName());
        aircraft.setCategory(category);
    }
    @IterableMapping(elementTargetType = AircraftResponseDto.class)
    List<AircraftResponseDto> toAircraftResponseDtoList(Collection<Aircraft> aircraftCollection);
}
