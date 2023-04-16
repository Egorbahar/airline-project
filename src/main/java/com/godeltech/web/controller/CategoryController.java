package com.godeltech.web.controller;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.AssignmentException;
import com.godeltech.mapper.CategoryMapper;
import com.godeltech.persistence.model.Category;
import com.godeltech.service.AircraftService;
import com.godeltech.service.CategoryService;
import com.godeltech.web.dto.request.CategoryRequestDto;
import com.godeltech.web.dto.response.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final AircraftService aircraftService;
    private final LocalMessageSource localMessageSource;
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> findById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Find category with id:{}", id);
        return new ResponseEntity<>(categoryMapper.toCategoryResponseDto(categoryService.findById(id)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAll() {
        log.info("Find all categories");
        return new ResponseEntity<>(categoryMapper.toCategoryResponseDtoList(categoryService.findAll()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> save(@Valid @RequestBody CategoryRequestDto categoryRequestDto) {
        log.info("Save new category");
        return new ResponseEntity<>((categoryMapper.toCategoryResponseDto(categoryService.save(categoryMapper.toCategory(categoryRequestDto)))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable @Validated @NotNull @Positive Long id) {
        log.info("Delete category with id:{}", id);
        final boolean isAssignedToFlight = aircraftService.findAll().stream()
                .map(flight -> flight.getCategory().getId())
                .toList()
                .contains(id);
        if (isAssignedToFlight) {
            throw new AssignmentException(localMessageSource.getMessage("error.record.isAssignment", new Object[]{}));
        } else {
            categoryService.deleteById(id);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryResponseDto> update(@PathVariable @Validated @NotNull @Positive Long id,
                                                      @RequestBody CategoryRequestDto categoryRequestDto) {
        log.info("Update category with id:{}", id);
        final Category category = categoryService.findById(id);
        categoryMapper.updateEntity(category,categoryRequestDto);
        return new ResponseEntity<>(categoryMapper.toCategoryResponseDto(categoryService.update(category)), HttpStatus.OK);
    }
}
