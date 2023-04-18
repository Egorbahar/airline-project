package com.godeltech.service;

import com.godeltech.persistence.model.Category;
import com.godeltech.persistence.reposirtory.CategoryRepository;
import com.godeltech.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {
    private final static Long ID = 1L;
    private Category expectedCategory;
    private List<Category> expectedCategoryList;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void init() {
        expectedCategory = Category.builder().id(ID).name("name").build();
        expectedCategoryList = new ArrayList<>(List.of(Category.builder().id(3L).name("name").build(),
                Category.builder().id(4L).name("name").build()));
    }

    @Test
    void testFindById() {
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryService.findById(ID);

        verify(categoryRepository, times(1)).findById(ID);

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    void testFindAll() {
        when(categoryRepository.findAll()).thenReturn(expectedCategoryList);

        List<Category> actualCategoryList = categoryService.findAll();

        verify(categoryRepository, times(1)).findAll();

        assertEquals(expectedCategoryList, actualCategoryList);
    }

    @Test
    void testSave() {
        when(categoryRepository.save(expectedCategory)).thenReturn(expectedCategory);

        Category actualCategory = categoryService.save(expectedCategory);

        verify(categoryRepository, times(1)).save(expectedCategory);

        assertEquals(expectedCategory, actualCategory);
    }

    @Test
    void testDeleteById() {
        doReturn(Optional.of(expectedCategory)).when(categoryRepository).findById(ID);

        categoryService.deleteById(ID);

        verify(categoryRepository, times(1)).deleteById(ID);

    }

    @Test
    void testUpdate() {
        doReturn(Optional.of(expectedCategory)).when(categoryRepository).findById(ID);

        when(categoryRepository.saveAndFlush(expectedCategory)).thenReturn(expectedCategory);

        Category actualCategory = categoryService.update(expectedCategory);

        verify(categoryRepository, times(1)).saveAndFlush(expectedCategory);

        assertEquals(expectedCategory, actualCategory);
    }
}
