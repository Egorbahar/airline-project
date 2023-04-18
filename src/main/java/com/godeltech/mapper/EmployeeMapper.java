package com.godeltech.mapper;

import com.godeltech.persistence.model.Employee;
import com.godeltech.web.dto.request.EmployeeRequestDto;
import com.godeltech.web.dto.response.EmployeeResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "surname", source = "surname")
    @Mapping(target = "flightCrewId", source = "flightCrew.id")
    EmployeeResponseDto toEmployeeResponseDto(Employee employee);

    @Mapping(target = "id", ignore = true)
    Employee toEmployee(EmployeeRequestDto employeeRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Employee employee, EmployeeRequestDto employeeRequestDto);

    @IterableMapping(elementTargetType = EmployeeResponseDto.class)
    List<EmployeeResponseDto> toEmployeeResponseDtoList(Collection<Employee> employees);
}
