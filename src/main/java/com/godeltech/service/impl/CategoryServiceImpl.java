package com.godeltech.service.impl;

import com.godeltech.component.LocalMessageSource;
import com.godeltech.exception.ResourceNotFoundException;
import com.godeltech.persistence.model.Category;
import com.godeltech.persistence.reposirtory.CategoryRepository;
import com.godeltech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final LocalMessageSource messageSource;

    @Override
    public Category findById(final Long id) {
        log.debug("Find category with id:{}", id);
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(messageSource.getMessage("error.record.notExist", new Object[]{})));
    }

    @Override
    @Transactional
    public Category save(final Category category) {
        log.debug("Save category");
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        log.debug("Find all categories with");
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public Category update(final Category category) {
        log.debug("Update category with id:{}", category.getId());
        findById(category.getId());
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    @Transactional
    public void deleteById(final Long id) {
        log.debug("Delete category with id:{}", id);
        findById(id);
        categoryRepository.deleteById(id);
    }
}
