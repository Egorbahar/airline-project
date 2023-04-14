package com.godeltech.mapper;

import com.godeltech.persistence.model.Category;
import com.godeltech.web.dto.request.CategoryRequestDto;
import com.godeltech.web.dto.response.CategoryResponseDto;
import org.mapstruct.*;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name" , source = "name")
    CategoryResponseDto toCategoryResponseDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name" , source = "name")
    Category toCategory(CategoryRequestDto categoryRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Category category, CategoryRequestDto categoryRequestDto);

    @IterableMapping(elementTargetType = CategoryResponseDto.class)
    List<CategoryResponseDto> toCategoryResponseDtoList(Collection<Category> categories);
}
